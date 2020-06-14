
package org.medihub.application.ports.incoming.appointment;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;
import java.io.NotActiveException;
import java.time.LocalDate;
import java.time.LocalTime;

public interface AddAppointmentUseCase {
    void addAppointment(AddAppointmentCommand addAppointmentCommand) throws NotFoundException, NotAvailableException, NotActiveException, ForbiddenException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddAppointmentCommand extends SelfValidating<AddAppointmentUseCase.AddAppointmentCommand> {

        @NotNull
        Long id;
        @NotNull
        LocalDate date;
        @NotNull
        LocalTime time;
        @NotNull
        Long doctorId;
        @NotNull
        Long clinicRoomId;

        public AddAppointmentCommand(
                Long id,
                LocalDate date,
                LocalTime time,
                Long doctorId,
                Long clinicRoomId) {
            this.id = id;
            this.date = date;
            this.time = time;
            this.doctorId = doctorId;
            this.clinicRoomId = clinicRoomId;
            this.validateSelf();
        }
    }
}