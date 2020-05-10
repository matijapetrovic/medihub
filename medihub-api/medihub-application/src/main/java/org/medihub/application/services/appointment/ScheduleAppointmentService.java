package org.medihub.application.services.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_request.ScheduleAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentRequestPort;
import org.medihub.application.ports.outgoing.appointment_type.LoadAppointmentTypePort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.domain.MedicalDoctor;
import org.medihub.domain.Money;
import org.medihub.domain.Patient;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.identity.Account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@RequiredArgsConstructor
public class ScheduleAppointmentService implements ScheduleAppointmentUseCase {
    private final LoadDoctorPort loadDoctorPort;
    private final LoadPatientPort loadPatientPort;
    private final SaveAppointmentRequestPort saveAppointmentRequestPort;
    private final GetAuthenticatedPort getAuthenticatedPort;

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
                        command.getTime());
        saveAppointmentRequestPort.saveAppointmentRequest(request);
    }

    private void ensureDoctorIsAvailable(MedicalDoctor doctor, LocalDate date, LocalTime time) {
        // check if time slot provided by date is available
    }
}
