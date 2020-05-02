package org.medihub.persistence.appointment_type;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.appointment.LoadAppointmentTypePort;
import org.medihub.domain.AppointmentType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentTypeAdapter implements LoadAppointmentTypePort {
    @Override
    public AppointmentType loadAppointmentType(Long id) {
        return null;
    }
}
