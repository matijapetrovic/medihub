package org.medihub.application.ports.outgoing.appointment_type;


import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;

public interface DeleteAppointmentTypePort {
    void delete(Long id) throws NotFoundException, ForbiddenException;
}
