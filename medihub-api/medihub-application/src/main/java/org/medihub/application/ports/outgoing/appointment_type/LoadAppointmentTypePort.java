package org.medihub.application.ports.outgoing.appointment_type;

import org.medihub.domain.AppointmentType;

public interface LoadAppointmentTypePort {
    AppointmentType loadAppointmentType(Long id);
    AppointmentType loadAppointmentType(String name);
}
