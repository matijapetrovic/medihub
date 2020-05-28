package org.medihub.application.services.appointment_type.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_type.GetAppointmentTypesOutput;
import org.medihub.application.ports.incoming.appointment_type.GetAppointmentTypesQuery;
import org.medihub.application.ports.outgoing.appointment_type.GetAppointmentTypesPort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetAppointmentTypeService implements GetAppointmentTypesQuery {
    private final GetAppointmentTypesPort getAppointmentTypesPort;

    @Override
    public List<GetAppointmentTypesOutput> getAppointmentTypes() {
        return getAppointmentTypesPort
                .getAppointmentTypes()
                .stream()
                .map(appointmentType ->
                        new GetAppointmentTypesOutput(
                            appointmentType.getId(),
                            appointmentType.getName()))
                .collect(Collectors.toList());
    }
}
