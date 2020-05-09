package org.medihub.application.services.appointment_type;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_type.AddAppointmentTypeUseCase;
import org.medihub.application.ports.outgoing.appointment_type.SaveAppointmentTypePort;
import org.medihub.domain.appointment.AppointmentType;

@RequiredArgsConstructor
public class AddAppointmentTypeService implements AddAppointmentTypeUseCase {
    private final SaveAppointmentTypePort saveAppointmentTypePort;

    @Override
    public void addAppointmentType(AddAppointmentTypeCommand addAppointmentTypeCommand) {
        saveAppointmentTypePort.saveAppointmentType(
                new AppointmentType(
                        addAppointmentTypeCommand.getId(),
                        addAppointmentTypeCommand.getName()
                        )
        );
    }
}
