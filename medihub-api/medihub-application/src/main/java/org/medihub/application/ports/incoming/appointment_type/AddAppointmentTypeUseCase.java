package org.medihub.application.ports.incoming.appointment_type;

import org.medihub.domain.AppointmentType;

public interface AddAppointmentTypeUseCase {
    void addAppointmentType(AppointmentType appointmentType);
}
