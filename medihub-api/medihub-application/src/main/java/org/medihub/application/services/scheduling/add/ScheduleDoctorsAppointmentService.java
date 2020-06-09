package org.medihub.application.services.scheduling.add;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.scheduling.ScheduleDoctorsAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentRequestPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.domain.Money;
import org.medihub.domain.account.Account;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.patient.Patient;

import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
public class ScheduleDoctorsAppointmentService implements ScheduleDoctorsAppointmentUseCase {
    private final LoadDoctorPort loadDoctorPort;
    private final LoadPatientPort loadPatientPort;
    private final SaveAppointmentRequestPort saveAppointmentRequestPort;
    private final GetAuthenticatedPort getAuthenticatedPort;

    @Override
    public void scheduleAppointment(ScheduleDoctorsAppointmentCommand command) {
        Account authenticated = getAuthenticatedPort.getAuthenticated();
        Patient patient = loadPatientPort.loadPatientByAccountId(command.getPatientId());
        MedicalDoctor medicalDoctor = loadDoctorPort.loadDoctor(authenticated.getId());
        Money price = medicalDoctor.getClinic().getPrice(medicalDoctor.getSpecialization());
        ensurePatientIsAvailable(patient, command.getDate(), command.getTime());
        AppointmentRequest request =
                new AppointmentRequest(
                        null,
                        medicalDoctor,
                        patient,
                        price,
                        command.getDate(),
                        command.getTime());
        saveAppointmentRequestPort.saveAppointmentRequest(request);
    }

    private void ensurePatientIsAvailable(Patient patient, LocalDate date, LocalTime time) {
        //TODO check if time slot provided by date is available
    }
}
