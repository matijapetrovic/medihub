package org.medihub.application.ports.outgoing.appointment;

public interface DeleteAppointmentPort {
    void deleteById(Long id);
}
