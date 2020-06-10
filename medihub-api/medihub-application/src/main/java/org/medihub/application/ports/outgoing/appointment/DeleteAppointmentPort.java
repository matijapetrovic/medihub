package org.medihub.application.ports.outgoing.appointment;

public interface DeleteAppointmentPort {
    void deleteAppointment(Long appointmentId);
}
