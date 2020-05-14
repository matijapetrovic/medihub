package org.medihub.application.ports.outgoing.appointment_type;

import org.medihub.domain.AppointmentType;

public interface RemoveAppointmentTypePort {
    AppointmentType remove(Long id);
}
