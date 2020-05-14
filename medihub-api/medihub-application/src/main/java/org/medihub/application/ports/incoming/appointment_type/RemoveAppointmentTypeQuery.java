package org.medihub.application.ports.incoming.appointment_type;

public interface RemoveAppointmentTypeQuery {
    RemoveAppointmentTypeOutput remove(Long id);
}
