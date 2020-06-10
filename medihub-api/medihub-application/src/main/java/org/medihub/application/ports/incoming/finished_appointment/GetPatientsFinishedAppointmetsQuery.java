package org.medihub.application.ports.incoming.finished_appointment;

import java.util.List;

public interface GetPatientsFinishedAppointmetsQuery {
    List<GetFinishedAppointmentOutput> getPatientsFinishedAppointments(Long patientId);
}
