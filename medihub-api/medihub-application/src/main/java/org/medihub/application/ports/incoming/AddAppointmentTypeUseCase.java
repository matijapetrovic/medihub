package org.medihub.application.ports.incoming;

import org.medihub.domain.AppointmentType;

public interface AddAppointmentTypeUseCase {
    void addAppointmentType(AppointmentType appointmentType);
}
