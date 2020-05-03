package org.medihub.application.ports.outgoing.appointment;

import org.medihub.domain.AppointmentType;

public interface LoadAppointmentTypePort {
    AppointmentType loadAppointmentType(Long id);
    AppointmentType loadAppointmentType(String name);
}
