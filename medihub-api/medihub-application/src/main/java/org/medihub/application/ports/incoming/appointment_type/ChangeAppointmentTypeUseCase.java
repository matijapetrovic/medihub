package org.medihub.application.ports.incoming.appointment_type;

import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface ChangeAppointmentTypeUseCase {
    void changeAppointmentType(ChangeAppointmentTypeCommand command);

    @Value
    class ChangeAppointmentTypeCommand extends SelfValidating<ChangeAppointmentTypeCommand> {
        @NotNull
        Long id;
        @NotBlank
        String name;
    }
}
