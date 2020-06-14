package org.medihub.application.ports.outgoing.appointment_request;

import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;

public interface DeleteAppointmentRequestPort {
    void deleteAppointmentRequest(Long id) throws NotFoundException, ForbiddenException;
}
