package org.medihub.application.ports.outgoing.finished_appointment;

import org.medihub.domain.appointment.FinishedAppointment;

import java.util.List;

public interface GetPatientsFinishedAppointmentsPort {
    List<FinishedAppointment> getPatientsFinishedAppointments(Long patientId);
}
