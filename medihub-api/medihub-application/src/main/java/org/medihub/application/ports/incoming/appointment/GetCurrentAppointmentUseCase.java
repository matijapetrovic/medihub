package org.medihub.application.ports.incoming.appointment;

import org.medihub.domain.appointment.Appointment;

public interface GetCurrentAppointmentUseCase {
    Appointment getCurrentAppointment(Long patientId);
}
