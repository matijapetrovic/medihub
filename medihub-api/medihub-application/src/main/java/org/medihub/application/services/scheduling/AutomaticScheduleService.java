package org.medihub.application.services.scheduling;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.application.ports.incoming.scheduling.AutomaticSchedulingUseCase;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.application.ports.outgoing.appointment_request.DeleteAppointmentRequestPort;
import org.medihub.application.ports.outgoing.appointment_request.GetAllAppointmentRequestsPort;
import org.medihub.application.ports.outgoing.appointment_request.LoadAppointmentRequestPort;
import org.medihub.application.ports.outgoing.clinic_room.AddAppointmentToClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.GetAllClinicRoomsPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.application.ports.outgoing.clinic_room.LoadRoomDailySchedulePort;
import org.medihub.application.ports.outgoing.doctor.AddAppointmentToMedicalDoctorSchedulePort;
import org.medihub.application.ports.outgoing.doctor.GetAllDoctorsPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorWorkingTimePort;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.LoadDoctorDailySchedulePort;
import org.medihub.domain.WorkingTime;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;

import javax.transaction.Transactional;
import java.io.NotActiveException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AutomaticScheduleService implements AutomaticSchedulingUseCase {
    private final Integer WORKING_HOURS = 24;

    private final GetAllAppointmentRequestsPort getAllAppointmentRequestsPort;
    private final GetAllDoctorsPort getAllDoctorsPort;
    private final GetAllClinicRoomsPort getAllClinicRoomsPort;
    private final DeleteAppointmentRequestPort deleteAppointmentRequestPort;
    private final LoadDoctorDailySchedulePort loadDoctorDailySchedulePort;
    private final GetDoctorWorkingTimePort getDoctorWorkingTimePort;
    private final LoadRoomDailySchedulePort loadRoomDailySchedulePort;
    private final SendEmailPort sendEmailPort;
    private final AddAppointmentToMedicalDoctorSchedulePort addAppointmentToMedicalDoctorSchedulePort;
    private final SaveAppointmentPort saveAppointmentPort;
    private final AddAppointmentToClinicRoomPort addAppointmentToClinicRoomPort;
    private final GetClinicRoomsPort getClinicRoomsPort;
    private final LoadAppointmentRequestPort loadAppointmentRequestPort;

    @Override
    @Transactional
    public void runAutomaticScheduleRoom() throws NotActiveException, ForbiddenException, NotFoundException, NotAvailableException {
        for (AppointmentRequest appointmentRequest: getAllAppointmentRequestsPort.getAll()) {
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
        List<String> availableTimes = getAvailableTimes(doctorId, date);
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
        for(ClinicRoom room: getAllClinicRoomsPort.getAll()) {
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
        for(String time: loadClinicRoomDailySchedule(clinicRoomId, date)) {
            if(LocalTime.parse(time).equals(findTime)) {
                return addAppointmentToSchedule(appointmentRequest, clinicRoomId);
            }
        }
        return false;
    }

    private boolean addAppointmentToSchedule(AppointmentRequest appointmentRequest, Long clinicRoomId) throws NotFoundException, NotAvailableException, NotActiveException, ForbiddenException {
        addAppointment(makeCommand(appointmentRequest, clinicRoomId));
        deleteAppointmentRequestPort.deleteAppointmentRequest(appointmentRequest.getId());
        notifyPatient(appointmentRequest);
        return true;
    }

    private AddAppointmentUseCase.AddAppointmentCommand makeCommand(AppointmentRequest appointmentRequest, Long clinicRoomId) {
        return new AddAppointmentUseCase.AddAppointmentCommand(
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

    private List<String> getAvailableTimes(Long doctorId, LocalDate date) {
        DailySchedule<MedicalDoctorScheduleItem> dailySchedule =
                loadDoctorDailySchedulePort.loadDailySchedule(doctorId, date);
        WorkingTime workingTime = getDoctorWorkingTimePort.getWorkingTime(doctorId);

        List<LocalTime> availableTimes = dailySchedule.getAvailableTimes(workingTime);
        if (date.equals(LocalDate.now().plusDays(1)))
            filterAvailableTimesForToday(availableTimes);

        return mapToOutput(availableTimes);
    }

    private void filterAvailableTimesForToday(List<LocalTime> availableTimes) {
        availableTimes.removeIf(time -> time.isBefore(LocalTime.now()));
    }

    private List<String> mapToOutput(List<LocalTime> availableTimes) {
        return availableTimes
                .stream()
                .map(LocalTime::toString)
                .collect(Collectors.toList());
    }

    private List<String> loadClinicRoomDailySchedule(Long clinicRoomId, LocalDate date) {
        DailySchedule<ClinicRoomScheduleItem> dailySchedule =
                loadRoomDailySchedulePort.loadClinicRoomDailySchedule(clinicRoomId, date);

        return buildAvailableTimesList(dailySchedule);
    }

    private List<String> buildAvailableTimesList(DailySchedule<ClinicRoomScheduleItem> dailySchedule) {
        LocalTime currentTime = LocalTime.parse("00:00");
        List<String> availableTimes = new ArrayList<>();
        for (int i = 0; i < WORKING_HOURS; i++) {
            if (dailySchedule.isAvailable(currentTime))
                availableTimes.add(currentTime.toString());
            currentTime = currentTime.plusHours(1L);
        }

        return availableTimes;
    }

    public void addAppointment(AddAppointmentUseCase.AddAppointmentCommand addAppointmentCommand) throws NotFoundException, NotAvailableException, ForbiddenException {
        AppointmentRequest appointmentRequest = loadAppointmentRequestPort.loadByIdWithLock(addAppointmentCommand.getId());
        ClinicRoom room = getClinicRoomsPort.getClinicRoomById(addAppointmentCommand.getClinicRoomId());

        Appointment appointment = new Appointment(
                null,
                appointmentRequest.getDate(),
                appointmentRequest.getTime(),
                appointmentRequest.getPatient(),
                appointmentRequest.getDoctor(),
                room,
                appointmentRequest.getPrice().getAmount()
        );
        appointment = saveAppointmentPort.saveAppointment(appointment);
        addAppointmentToClinicRoomPort.addAppointmentToClinicRoom(
                getClinicRoomsPort.getClinicRoomById(addAppointmentCommand.getClinicRoomId()),
                appointmentRequest.getDate(),
                appointmentRequest.getTime()
        );
        addAppointmentToMedicalDoctorSchedulePort.addAppointmentToSchedule(
                appointmentRequest.getDate(),
                appointmentRequest.getTime(),
                appointment);

        deleteAppointmentRequestPort.deleteAppointmentRequest(appointmentRequest.getId());
        notifyDoctor(appointment);
        notifyPatient(appointment);
    }

    private void notifyDoctor(Appointment appointment) {
        String to = appointment.getDoctor().getPersonalInfo().getAccount().getEmail();
        String subject = "Appointment request notification";
        String text = String.format("Appointment has been scheduled with patient %s at %s",
                appointment.getPatient().getFullName(),
                LocalDateTime.of(appointment.getDate(), appointment.getTime()));
        sendEmailPort.sendEmail(to, subject, text);
    }

    private void notifyPatient(Appointment appointment) {
        String to = appointment.getPatient().getPersonalInfo().getAccount().getEmail();
        String subject = "Appointment request notification";
        String text = String.format("Appointment has been scheduled with %s at %s",
                appointment.getDoctor().getFullName(),
                LocalDateTime.of(appointment.getDate(), appointment.getTime()));
        sendEmailPort.sendEmail(to, subject, text);
    }
}
