package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.AddAppointmentTypeUseCase;
import org.medihub.application.ports.outgoing.LoadAppointmentTypePort;
import org.medihub.application.ports.outgoing.SaveAppointmentTypePort;
import org.medihub.domain.AppointmentType;

@RequiredArgsConstructor
public class AppointmentTypeService implements AddAppointmentTypeUseCase {
    private final LoadAppointmentTypePort loadAppointmentTypePort;
    private final SaveAppointmentTypePort saveAppointmentTypePort;

    @Override
    public void addAppointmentType(AppointmentType appointmentType) {
        saveAppointmentTypePort.saveAppointmentType(appointmentType);
    }
}
