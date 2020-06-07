package org.medihub.application.ports.incoming.scheduling;

import javax.validation.constraints.NotNull;

public interface SchedulePredefinedAppointmentUseCase {
    void schedulePredefinedAppointment(@NotNull Long appointmentId);
}
