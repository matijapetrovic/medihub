package org.medihub.application.ports.outgoing.appointment_type;

import org.medihub.domain.appointment.AppointmentType;

import java.util.List;

public interface GetAppointmentTypesPort {
    List<AppointmentType> getAppointmentTypes();

    AppointmentType getById(Long id);
}
