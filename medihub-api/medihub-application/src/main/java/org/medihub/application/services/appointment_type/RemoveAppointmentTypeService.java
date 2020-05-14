package org.medihub.application.services.appointment_type;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_type.RemoveAppointmentTypeOutput;
import org.medihub.application.ports.incoming.appointment_type.RemoveAppointmentTypeQuery;
import org.medihub.application.ports.outgoing.appointment_type.RemoveAppointmentTypePort;
import org.medihub.domain.AppointmentType;

@RequiredArgsConstructor
public class RemoveAppointmentTypeService implements RemoveAppointmentTypeQuery {
    private final RemoveAppointmentTypePort removeAppointmentTypePort;


    @Override
    public RemoveAppointmentTypeOutput remove(Long id) {
        AppointmentType appointmentType = removeAppointmentTypePort.remove(id);
        return new RemoveAppointmentTypeOutput(appointmentType.getId(), appointmentType.getName());
    }
}
