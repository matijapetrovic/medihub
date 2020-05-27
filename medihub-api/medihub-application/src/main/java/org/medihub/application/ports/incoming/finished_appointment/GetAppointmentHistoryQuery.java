package org.medihub.application.ports.incoming.finished_appointment;

import java.util.List;

public interface GetAppointmentHistoryQuery {
    List<GetAppointmentHistoryOutput> getAppointmentHistory();
}
