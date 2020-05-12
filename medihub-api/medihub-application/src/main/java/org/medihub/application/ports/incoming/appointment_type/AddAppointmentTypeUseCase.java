package org.medihub.application.ports.incoming.appointment_type;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

public interface AddAppointmentTypeUseCase {
    void addAppointmentType(AddAppointmentTypeCommand addAppointmentTypeCommand);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddAppointmentTypeCommand extends SelfValidating<AddAppointmentTypeUseCase.AddAppointmentTypeCommand> {

        Long id;
        String name;

        public AddAppointmentTypeCommand(
                Long id,
                String name) {
            this.id = id;
            this.name = name;
            this.validateSelf();
        }
    }
}
