package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.appointment_type.AddAppointmentTypeUseCase;
import org.medihub.application.ports.outgoing.appointment_type.SaveAppointmentTypePort;
import org.medihub.domain.AppointmentType;

@RequiredArgsConstructor
public class AddAppointmentTypeService implements AddAppointmentTypeUseCase {
    private final SaveAppointmentTypePort saveAppointmentTypePort;

    @Override
    public void addAppointmentType(AppointmentType appointmentType) {
        saveAppointmentTypePort.saveAppointmentType(appointmentType);
    }
}
