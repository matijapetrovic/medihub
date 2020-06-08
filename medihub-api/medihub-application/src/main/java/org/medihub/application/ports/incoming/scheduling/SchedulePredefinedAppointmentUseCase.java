package org.medihub.application.ports.incoming.scheduling;

import org.medihub.application.exceptions.NotAvailableException;

import javax.validation.constraints.NotNull;

public interface SchedulePredefinedAppointmentUseCase {
    void schedulePredefinedAppointment(@NotNull Long appointmentId) throws NotAvailableException;
}
