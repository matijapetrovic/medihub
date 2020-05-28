package org.medihub.application.ports.outgoing.finished_appointment;

import org.medihub.domain.appointment.FinishedAppointment;

import java.util.List;
import java.util.Set;

public interface GetFinishedAppointmentsPort {
    List<FinishedAppointment> getFinishedAppointments(Long patientId);
}
