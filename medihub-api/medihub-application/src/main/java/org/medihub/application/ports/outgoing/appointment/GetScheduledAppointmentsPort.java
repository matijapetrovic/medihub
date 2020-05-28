package org.medihub.application.ports.outgoing.appointment;

import org.medihub.domain.appointment.Appointment;

import java.util.List;

public interface GetScheduledAppointmentsPort {
    List<Appointment> getAppointments(Long patientId);
}
