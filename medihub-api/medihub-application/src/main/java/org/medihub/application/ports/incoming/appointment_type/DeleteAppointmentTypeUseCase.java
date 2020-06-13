package org.medihub.application.ports.incoming.appointment_type;

import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;

public interface DeleteAppointmentTypeUseCase {
    void delete(Long id) throws NotFoundException, ForbiddenException;
}
