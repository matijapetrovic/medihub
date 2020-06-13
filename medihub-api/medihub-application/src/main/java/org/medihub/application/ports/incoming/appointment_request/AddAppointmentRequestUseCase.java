package org.medihub.application.ports.incoming.appointment_request;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;

public interface AddAppointmentRequestUseCase {
    void addAppointment(AddAppointmentRequestCommand addAppointmentCommand);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddAppointmentRequestCommand extends SelfValidating<AddAppointmentRequestUseCase.AddAppointmentRequestCommand> {

        @NotNull
        Long id;
        @NotNull
        Long clinicRoomId;

        public AddAppointmentRequestCommand(
                Long id,
                Long clinicRoomId) {
            this.id = id;
            this.clinicRoomId = clinicRoomId;
        }
    }
}
