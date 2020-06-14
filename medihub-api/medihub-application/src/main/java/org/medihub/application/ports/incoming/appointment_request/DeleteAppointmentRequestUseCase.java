package org.medihub.application.ports.incoming.appointment_request;

import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;

public interface DeleteAppointmentRequestUseCase {
    void deleteAppointmentRequest(Long id) throws NotFoundException, ForbiddenException;
}
