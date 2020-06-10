package org.medihub.application.services.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment.GetCurrentAppointmentUseCase;
import org.medihub.application.ports.outgoing.appointment.GetCurrentAppointmentPort;
import org.medihub.domain.appointment.Appointment;

@RequiredArgsConstructor
public class GetCurrentAppointmentService implements GetCurrentAppointmentUseCase {
    private final GetCurrentAppointmentPort getCurrentAppointmentPort;

    @Override
    public Appointment getCurrentAppointment(Long doctorId, Long patientId) {
        return getCurrentAppointmentPort.getCurrentAppointment(doctorId, patientId);
    }
}
