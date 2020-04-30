package org.medihub.application.ports.outgoing;

import org.medihub.domain.AppointmentType;

public interface SaveAppointmentTypePort {
    void saveAppointmentType(AppointmentType appointmentType);
}
