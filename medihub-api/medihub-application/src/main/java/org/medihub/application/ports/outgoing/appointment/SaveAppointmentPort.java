package org.medihub.application.ports.outgoing.appointment;

import org.medihub.domain.appointment.Appointment;

public interface SaveAppointmentPort {
    void saveAppointment(Appointment appointment);
}
