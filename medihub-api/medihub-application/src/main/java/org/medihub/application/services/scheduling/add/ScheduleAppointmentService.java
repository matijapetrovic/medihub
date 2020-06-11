package org.medihub.application.services.scheduling.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.scheduling.ScheduleAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentRequestPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.Money;
import org.medihub.domain.patient.Patient;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.domain.account.Account;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
public class ScheduleAppointmentService implements ScheduleAppointmentUseCase {
    private final LoadDoctorPort loadDoctorPort;
    private final LoadPatientPort loadPatientPort;
    private final SaveAppointmentRequestPort saveAppointmentRequestPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final SendEmailPort sendEmailPort;

    @Override
    public void scheduleAppointment(ScheduleAppointmentCommand command) {
        Account authenticated = getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(authenticated.getId());
        MedicalDoctor medicalDoctor = loadDoctorPort.loadDoctor(command.getDoctorId());
        Money price = medicalDoctor.getClinic().getPrice(medicalDoctor.getSpecialization());
        ensureDoctorIsAvailable(medicalDoctor, command.getDate(), command.getTime());
        AppointmentRequest request =
                new AppointmentRequest(
                        null,
                        medicalDoctor,
                        patient,
                        price,
                        command.getDate(),
                        command.getTime(),
                        command.getType());
        saveAppointmentRequestPort.saveAppointmentRequest(request);
        notifyClinicAdmin(request);
    }

    private void ensureDoctorIsAvailable(MedicalDoctor doctor, LocalDate date, LocalTime time) {
        //TODO check if time slot provided by date is available
    }

    private void notifyClinicAdmin(AppointmentRequest request) {
        String to = "medihub.mail@gmail.com";
        String subject = "Appointment request notification";
        String text = String.format("Patient %s has request an appointment with %s at %s",
                request.getPatient().getFullName(),
                request.getDoctor().getFullName(),
                LocalDateTime.of(request.getDate(), request.getTime()));
        sendEmailPort.sendEmail(to, subject, text);
    }
}
