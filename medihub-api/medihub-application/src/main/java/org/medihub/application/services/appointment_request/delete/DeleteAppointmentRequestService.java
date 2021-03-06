package org.medihub.application.services.appointment_request.delete;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment_request.DeleteAppointmentRequestUseCase;
import org.medihub.application.ports.outgoing.appointment_request.DeleteAppointmentRequestPort;

@RequiredArgsConstructor
public class DeleteAppointmentRequestService implements DeleteAppointmentRequestUseCase {
    private final DeleteAppointmentRequestPort deleteAppointmentRequestPort;

    @Override
    public void deleteAppointmentRequest(Long id) throws ForbiddenException, NotFoundException {
        deleteAppointmentRequestPort.deleteAppointmentRequest(id);
    }
}
