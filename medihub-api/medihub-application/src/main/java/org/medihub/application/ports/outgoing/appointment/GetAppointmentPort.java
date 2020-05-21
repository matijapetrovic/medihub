package org.medihub.application.ports.outgoing.appointment;

import org.medihub.domain.appointment.Appointment;

public interface GetAppointmentPort {
    Appointment getAppointmentById(Long id);
}
