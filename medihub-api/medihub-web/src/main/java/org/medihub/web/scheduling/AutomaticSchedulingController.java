package org.medihub.web.scheduling;


import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase.AddAppointmentCommand;
import org.medihub.application.ports.incoming.appointment_request.DeleteAppointmentRequestUseCase;
import org.medihub.application.ports.incoming.appointment_request.GetAllAppointmentRequestsUseCase;
import org.medihub.application.ports.incoming.clinic_room.GetAllClinicRoomsUseCase;
import org.medihub.application.ports.incoming.clinic_room.GetRoomAvailableTimeQuery;
import org.medihub.application.ports.incoming.medical_doctor.GetAllMedicalDoctorsUseCase;
import org.medihub.application.ports.incoming.scheduling.GetDoctorAvailableTimesQuery;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.NotActiveException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Component
@RestController
public class AutomaticSchedulingController {
    private final GetAllAppointmentRequestsUseCase getAllAppointmentRequestsUseCase;
    private final GetAllMedicalDoctorsUseCase getAllMedicalDoctorsUseCase;
    private final GetAllClinicRoomsUseCase getAllClinicRoomsUseCase;
    private final AddAppointmentUseCase addAppointmentUseCase;
    private final DeleteAppointmentRequestUseCase deleteAppointmentRequestUseCase;
    private final GetDoctorAvailableTimesQuery getDoctorAvailableTimesQuery;
    private final GetRoomAvailableTimeQuery getRoomAvailableTimeQuery;
    private final SendEmailPort sendEmailPort;

    @Scheduled(cron = "${schedule.cron}")
    void runAutomaticScheduling() throws NotFoundException, NotAvailableException, NotActiveException, ForbiddenException {
        for (AppointmentRequest appointmentRequest: getAllAppointmentRequestsUseCase.getAll()) {
            searchForMatchingDate(appointmentRequest);
        }
    }

    private void searchForMatchingDate(AppointmentRequest appointmentRequest) throws NotFoundException, NotAvailableException, NotActiveException, ForbiddenException {
        LocalDate currentDate = appointmentRequest.getDate();
        while(!doctorFound(currentDate, appointmentRequest))
            currentDate = currentDate.plusDays(1L);
    }

    private boolean doctorFound(LocalDate date, AppointmentRequest appointmentRequest) throws NotFoundException, NotAvailableException, NotActiveException, ForbiddenException {
        if(doctorAvailableTimeFound(date,appointmentRequest.getDoctor().getId(),
                appointmentRequest.getDoctor().getClinic().getId(), appointmentRequest))
            return true;
        return false;
    }

    private boolean doctorAvailableTimeFound(
            LocalDate date,
            Long doctorId,
            Long doctorClinicId,
            AppointmentRequest appointmentRequest) throws NotFoundException, NotAvailableException, NotActiveException, ForbiddenException {
        List<String> availableTimes = getDoctorAvailableTimesQuery.getAvailableTimes(doctorId, date);
        if(availableTimes.contains(appointmentRequest.getTime().toString()) &&
                roomFound(date,appointmentRequest.getTime(), doctorClinicId, appointmentRequest))
            return true;
        for (String time: availableTimes) {
            if(roomFound(date, LocalTime.parse(time), doctorClinicId, appointmentRequest))
                return true;
        }
        return false;
    }

    private boolean roomFound(
            LocalDate date,
            LocalTime time,
            Long doctorClinicId,
            AppointmentRequest appointmentRequest) throws NotFoundException, NotAvailableException, NotActiveException, ForbiddenException {
        for(ClinicRoom room: getAllClinicRoomsUseCase.getAll()) {
            if(roomAvailableTimeFound(date, room.getId(), time, appointmentRequest)
                    && room.getClinic().getId().equals(doctorClinicId))
                return true;
        }
        return false;
    }

    private boolean roomAvailableTimeFound(
            LocalDate date,
            Long clinicRoomId,
            LocalTime findTime,
            AppointmentRequest appointmentRequest) throws NotFoundException, NotAvailableException, NotActiveException, ForbiddenException {
        List<String> times = getRoomAvailableTimeQuery.loadClinicRoomDailySchedule(clinicRoomId, date);
        for(String time: getRoomAvailableTimeQuery.loadClinicRoomDailySchedule(clinicRoomId, date)) {
            if(LocalTime.parse(time).equals(findTime)) {
                return addAppointment(appointmentRequest, clinicRoomId);
            }
        }
        return false;
    }

    private boolean addAppointment(AppointmentRequest appointmentRequest, Long clinicRoomId) throws NotFoundException, NotAvailableException, NotActiveException, ForbiddenException {
        addAppointmentUseCase.addAppointment(makeCommand(appointmentRequest, clinicRoomId));
        deleteAppointmentRequestUseCase.deleteAppointmentRequest(appointmentRequest.getId());
        notifyPatient(appointmentRequest);
        return true;
    }

    private AddAppointmentCommand makeCommand(AppointmentRequest appointmentRequest, Long clinicRoomId) {
        return new AddAppointmentCommand(
           appointmentRequest.getId(),
                clinicRoomId
        );
    }

    private void notifyPatient(AppointmentRequest appointmentRequest) {
        String to = appointmentRequest.getPatient().getPersonalInfo().getAccount().getEmail();
        String subject = "Appointment request notification";
        String text = String.format("Your appointment request with doctor %s has been accepted at %s",
                appointmentRequest.getDoctor().getFullName(),
                LocalDateTime.of(appointmentRequest.getDate(),appointmentRequest.getTime()).toString());
        sendEmailPort.sendEmail(to, subject, text);
    }
}
