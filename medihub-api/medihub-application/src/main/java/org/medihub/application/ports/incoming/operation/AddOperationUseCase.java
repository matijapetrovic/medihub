package org.medihub.application.ports.incoming.operation;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.appointment.AddAppointmentUseCase;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AddOperationUseCase {
    OperationOutput addOperation(AddOperationCommand command) throws NotFoundException, ForbiddenException, NotAvailableException;

    @Value
    class AddOperationCommand extends SelfValidating<AddOperationCommand> {
        @NotNull
        Long requestId;
        @NotNull
        LocalDate date;
        @NotNull
        LocalTime time;
        @NotNull
        Long doctorId;
        @NotNull
        Long clinicRoomId;
        @NotNull
        List<Long> presentDoctors;

        public AddOperationCommand(
                Long requestId,
                LocalDate date,
                LocalTime time,
                Long doctorId,
                Long clinicRoomId,
                List<Long> presentDoctors) {
            this.requestId = requestId;
            this.date = date;
            this.time = time;
            this.doctorId = doctorId;
            this.clinicRoomId = clinicRoomId;
            this.presentDoctors = presentDoctors;
            this.validateSelf();
        }
    }
}
