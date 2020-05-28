package org.medihub.application.ports.incoming.finished_appointment;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.common.SelfValidating;
import org.medihub.domain.appointment.FinishedAppointment;

import javax.validation.constraints.NotNull;

public interface AddFinishedAppointmentUseCase {
    public FinishedAppointmentOutput addFinishedAppointment(AddFinishedAppointmentCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddFinishedAppointmentCommand extends SelfValidating<AddFinishedAppointmentCommand> {
        @NotNull
        Long itemId;
        @NotNull
        String description;
        @NotNull
        Long appointment;
        @NotNull
        Long[] drugs;
        @NotNull
        Long diagnosis;

    }
}


