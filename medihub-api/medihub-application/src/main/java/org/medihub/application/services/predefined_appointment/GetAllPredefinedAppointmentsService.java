package org.medihub.application.services.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.predefined_appointment.GetAllPredefinedAppointmentsUseCase;
import org.medihub.application.ports.outgoing.predefined_appointment.GetAllPredefinedAppointmentsPort;
import org.medihub.domain.appointment.PredefinedAppointment;

import java.util.List;

@RequiredArgsConstructor
public class GetAllPredefinedAppointmentsService implements GetAllPredefinedAppointmentsUseCase {
    private final GetAllPredefinedAppointmentsPort getAllPredefinedAppointmentsPort;

    @Override
    public List<PredefinedAppointment> getAll() {
        return getAllPredefinedAppointmentsPort.getAll();
    }
}
