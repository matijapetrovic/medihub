package org.medihub.application.ports.outgoing.predefined_appointment;

import org.medihub.domain.appointment.PredefinedAppointment;

public interface LoadPredefinedAppointmentPort {
    PredefinedAppointment loadPredefinedAppointment(Long appointmentId);
}
