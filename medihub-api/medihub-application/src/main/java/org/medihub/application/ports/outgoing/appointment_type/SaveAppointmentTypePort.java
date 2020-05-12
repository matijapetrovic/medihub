package org.medihub.application.ports.outgoing.appointment_type;

import org.medihub.domain.appointment.AppointmentType;

public interface SaveAppointmentTypePort {
    void saveAppointmentType(AppointmentType appointmentType);
}
