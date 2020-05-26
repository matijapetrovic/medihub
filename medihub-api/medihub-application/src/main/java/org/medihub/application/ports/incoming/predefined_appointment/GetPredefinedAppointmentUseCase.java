package org.medihub.application.ports.incoming.predefined_appointment;

import org.medihub.domain.appointment.PredefinedAppointment;

import java.util.List;

public interface GetPredefinedAppointmentUseCase {
    List<PredefinedAppointment> loadAll();
}
