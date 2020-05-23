package org.medihub.application.ports.outgoing.appointment;

import org.medihub.domain.appointment.Appointment;

public interface SaveAppointmentPort {
    Appointment saveAppointment(Appointment appointment);
}
