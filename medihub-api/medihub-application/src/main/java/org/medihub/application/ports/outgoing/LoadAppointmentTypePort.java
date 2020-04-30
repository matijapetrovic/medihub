package org.medihub.application.ports.outgoing;

import org.medihub.domain.AppointmentType;

public interface LoadAppointmentTypePort {
    AppointmentType loadAppointmentType(String name);
}
