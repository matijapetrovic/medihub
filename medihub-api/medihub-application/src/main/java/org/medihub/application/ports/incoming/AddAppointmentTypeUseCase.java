package org.medihub.application.ports.incoming;

import org.medihub.domain.AppointmentType;

public interface AddAppointmentType {
    void addAppointmentType(AppointmentType appointmentType);
}
