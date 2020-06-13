package org.medihub.application.ports.incoming.operation;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface AddOperationUseCase {
    OperationOutput addOperation(AddOperationCommand command) throws NotFoundException, ForbiddenException;

    @Value
    class AddOperationCommand extends SelfValidating<AddOperationCommand> {
        @NotNull
        Long requestId;
        @NotNull
        Long clinicRoomId;
        @NotNull
        List<Long> presentDoctors;

        public AddOperationCommand(Long requestId, Long clinicRoomId, List<Long> presentDoctors) {
            this.requestId = requestId;
            this.clinicRoomId = clinicRoomId;
            this.presentDoctors = presentDoctors;
            this.validateSelf();
        }
    }
}
