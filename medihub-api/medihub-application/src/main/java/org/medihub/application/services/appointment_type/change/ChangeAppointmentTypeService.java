package org.medihub.application.services.appointment_type.change;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_type.ChangeAppointmentTypeUseCase;
import org.medihub.application.ports.outgoing.appointment_type.GetAppointmentTypePort;
import org.medihub.application.ports.outgoing.appointment_type.LoadAppointmentTypePort;
import org.medihub.application.ports.outgoing.appointment_type.SaveAppointmentTypePort;
import org.medihub.domain.appointment.AppointmentType;

@RequiredArgsConstructor
public class ChangeAppointmentTypeService implements ChangeAppointmentTypeUseCase {
    private final GetAppointmentTypePort getAppointmentTypePort;
    private final SaveAppointmentTypePort saveAppointmentTypePort;

    @Override
    public void changeAppointmentType(ChangeAppointmentTypeCommand command) {
        AppointmentType at = getAppointmentTypePort.getAppointmentType(command.getId());

        at.setName(command.getName());
        saveAppointmentTypePort.saveAppointmentType(at);
    }
}
