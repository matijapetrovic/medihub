package org.medihub.application.ports.outgoing.finished_appointment;

import org.medihub.domain.appointment.FinishedAppointment;

import java.util.Set;

public interface GetFinishedAppointmentsPort {
    Set<FinishedAppointment> getFinishedAppointments(Long patientId);
}
