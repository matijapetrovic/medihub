package org.medihub.application.ports.incoming.appointment_type;

import java.util.List;

public interface GetAppointmentTypesQuery {
    List<GetAppointmentTypesOutput> getAppointmentTypes();
}
