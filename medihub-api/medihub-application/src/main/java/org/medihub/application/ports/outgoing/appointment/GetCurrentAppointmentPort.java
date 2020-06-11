package org.medihub.application.ports.outgoing.appointment;

import org.medihub.domain.appointment.Appointment;

public interface GetCurrentAppointmentPort {
    Appointment getCurrentAppointment(Long doctorId, Long patientId);
}
