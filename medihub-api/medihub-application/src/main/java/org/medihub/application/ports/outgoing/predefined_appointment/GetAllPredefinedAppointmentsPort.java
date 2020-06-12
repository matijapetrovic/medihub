package org.medihub.application.ports.outgoing.predefined_appointment;

import org.medihub.domain.appointment.PredefinedAppointment;

import java.util.List;

public interface GetAllPredefinedAppointmentsPort {
    List<PredefinedAppointment> getAll();
}
