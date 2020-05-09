package org.medihub.application.services.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_request.ScheduleAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentRequestPort;
import org.medihub.application.ports.outgoing.appointment_type.LoadAppointmentTypePort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.application.ports.outgoing.patient.LoadPatientPort;
import org.medihub.domain.MedicalDoctor;
import org.medihub.domain.Patient;
import org.medihub.domain.appointment.AppointmentRequest;
import org.medihub.domain.appointment.AppointmentType;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ScheduleAppointmentService implements ScheduleAppointmentUseCase {
    private final LoadDoctorPort loadDoctorPort;
    private final LoadPatientPort loadPatientPort;
    private final LoadAppointmentTypePort loadAppointmentTypePort;
    private final SaveAppointmentRequestPort saveAppointmentRequestPort;

    @Override
    public void scheduleAppointment(ScheduleAppointmentCommand command) {
        // add validation
        Patient patient = loadPatientPort.loadPatient(command.getPatientId());
        MedicalDoctor medicalDoctor = loadDoctorPort.loadDoctor(command.getDoctorId());
        AppointmentType appointmentType = loadAppointmentTypePort.loadAppointmentType(command.getAppointmentTypeId());
        // get price of appointment for clinic
        AppointmentRequest request =
                new AppointmentRequest(
                    null,
                    medicalDoctor,
                    patient,
                    appointmentType,
                    BigDecimal.ZERO);

        saveAppointmentRequestPort.saveAppointmentRequest(request);
    }
}
