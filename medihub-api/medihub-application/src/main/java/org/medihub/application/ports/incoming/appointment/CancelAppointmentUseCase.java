package org.medihub.application.ports.incoming.appointment;

import org.medihub.application.exceptions.ForbiddenException;

import javax.validation.constraints.NotNull;

public interface CancelAppointmentUseCase {
    void cancelAppointment(@NotNull Long appointmentId) throws ForbiddenException;
}
