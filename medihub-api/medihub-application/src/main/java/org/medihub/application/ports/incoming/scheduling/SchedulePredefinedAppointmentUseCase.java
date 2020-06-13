package org.medihub.application.ports.incoming.scheduling;

import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;

import javax.validation.constraints.NotNull;

public interface SchedulePredefinedAppointmentUseCase {
    void schedulePredefinedAppointment(@NotNull Long appointmentId) throws NotAvailableException, NotFoundException;
}
