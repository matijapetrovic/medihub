package org.medihub.application.ports.outgoing.appointment_type;

import org.medihub.domain.AppointmentType;

import java.util.List;

public interface GetAppointmentTypesPort {
    List<AppointmentType> getAppointmentTypes();
}
