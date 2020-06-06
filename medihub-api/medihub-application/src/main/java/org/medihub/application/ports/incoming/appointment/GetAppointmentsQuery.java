package org.medihub.application.ports.incoming.appointment;

import java.util.List;

public interface GetAppointmentsQuery {
    List<GetAppointmentsOutput> getAppointments();
}
