package org.medihub.application.services.scheduling.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.scheduling.ScheduleAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentRequestPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.LoadDoctorDailySchedulePort;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.Money;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.patient.Patient;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.domain.account.Account;
import org.medihub.domain.scheduling.DailySchedule;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
public class ScheduleAppointmentService implements ScheduleAppointmentUseCase {
    private final LoadDoctorPort loadDoctorPort;
    private final LoadPatientPort loadPatientPort;
    private final LoadDoctorDailySchedulePort loadDoctorDailySchedulePort;
    private final SaveAppointmentRequestPort saveAppointmentRequestPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final SendEmailPort sendEmailPort;

    @Override
    @Transactional
    public void scheduleAppointment(ScheduleAppointmentCommand command) throws NotFoundException, NotAvailableException {
        Account authenticated = getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(authenticated.getId());

        MedicalDoctor medicalDoctor = loadDoctorPort.loadDoctor(command.getDoctorId());
        ensureDoctorIsAvailable(medicalDoctor, command.getDate(), command.getTime());

        AppointmentRequest request = createAppointmentRequest(patient, medicalDoctor, command);
        saveAppointmentRequestPort.saveAppointmentRequest(request);

        notifyClinicAdmin(request);
    }

    private void ensureDoctorIsAvailable(MedicalDoctor doctor, LocalDate date, LocalTime time) throws NotAvailableException {
        DailySchedule<MedicalDoctorScheduleItem> dailySchedule =
                loadDoctorDailySchedulePort.loadDailySchedule(doctor.getId(), date);
        if (!dailySchedule.isAvailable(time)) {
            throw new NotAvailableException("Doctor is unavailable");
        }
    }

    private AppointmentRequest createAppointmentRequest(
            Patient patient,
            MedicalDoctor doctor,
            ScheduleAppointmentCommand command) {
        return new AppointmentRequest(
                null,
                doctor,
                patient,
                doctor.getClinic().getPrice(doctor.getSpecialization()),
                command.getDate(),
                command.getTime(),
                command.getType());
    }

    private void notifyClinicAdmin(AppointmentRequest request) {
        String to = "medihub.mail@gmail.com";
        String subject = "Appointment request notification";
        String text = String.format("Patient %s has requested an %s with %s at %s",
                request.getType().equalsIgnoreCase("appointment") ?
                    "appointment" : "operation",
                request.getPatient().getFullName(),
                request.getDoctor().getFullName(),
                LocalDateTime.of(request.getDate(), request.getTime()));
        sendEmailPort.sendEmail(to, subject, text);
    }
}
