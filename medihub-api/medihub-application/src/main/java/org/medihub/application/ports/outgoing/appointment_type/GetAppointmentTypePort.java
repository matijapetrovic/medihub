package org.medihub.application.ports.outgoing.appointment_type;

import org.medihub.domain.appointment.AppointmentType;

public interface GetAppointmentTypePort {
    AppointmentType getAppointmentType(Long id);
}
