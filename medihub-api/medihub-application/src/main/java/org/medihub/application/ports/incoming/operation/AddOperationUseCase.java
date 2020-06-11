package org.medihub.application.ports.incoming.operation;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface AddOperationUseCase {
    OperationOutput addOperation(AddOperationCommand command) throws NotFoundException;

    @Value
    class AddOperationCommand extends SelfValidating<AddOperationCommand> {
        @NotBlank
        String date;
        @NotBlank
        String time;
        @NotNull
        Long patientId;
        @NotNull
        Long doctorId;
        @NotNull
        Long clinicRoomId;
        @NotNull
        List<Long> presentDoctors;
    }
}
