package org.medihub.application.services.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.predefined_appointment.GetPredefinedAppointmentsOutput;
import org.medihub.application.ports.incoming.predefined_appointment.GetPredefinedAppointmentsQuery;
import org.medihub.application.ports.outgoing.predefined_appointment.GetPredefinedAppointmentsPort;
import org.medihub.domain.appointment.PredefinedAppointment;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetPredefinedAppointmentsService implements GetPredefinedAppointmentsQuery {
    private final GetPredefinedAppointmentsPort getPredefinedAppointmentsPort;

    @Override
    public List<GetPredefinedAppointmentsOutput> getPredefinedAppointments(Long clinicId) {
        List<PredefinedAppointment> predefinedAppointments =
                getPredefinedAppointmentsPort.getPredefinedAppointments(clinicId);
        return mapToOutput(predefinedAppointments);
    }

    private List<GetPredefinedAppointmentsOutput> mapToOutput(List<PredefinedAppointment> predefinedAppointments) {
        return predefinedAppointments
                .stream()
                .map(pa -> new GetPredefinedAppointmentsOutput(
                        pa.getId(),
                        pa.getAppointmentType().getName(),
                        pa.getDoctor().getFullName(),
                        pa.getClinicRoom().getName(),
                        pa.getDate().toString(),
                        pa.getStart().toString(),
                        pa.getPrice()
                ))
                .collect(Collectors.toList());
    }
}
