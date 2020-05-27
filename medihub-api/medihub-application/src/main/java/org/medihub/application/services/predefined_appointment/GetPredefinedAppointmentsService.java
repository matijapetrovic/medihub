package org.medihub.application.services.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.predefined_appointment.GetPredefinedAppointmentsQuery;
import org.medihub.application.ports.outgoing.predefined_appointment.GetPredefinedAppointmentsPort;
import org.medihub.domain.appointment.PredefinedAppointment;

import java.util.List;

@RequiredArgsConstructor
public class GetPredefinedAppointmentsService implements GetPredefinedAppointmentsQuery {
    private final GetPredefinedAppointmentsPort getPredefinedAppointmentsPort;

    @Override
    public List<PredefinedAppointment> getPredefinedAppointments(Long clinicId) {
        return getPredefinedAppointmentsPort.getPredefinedAppointments(clinicId);
    }
}
