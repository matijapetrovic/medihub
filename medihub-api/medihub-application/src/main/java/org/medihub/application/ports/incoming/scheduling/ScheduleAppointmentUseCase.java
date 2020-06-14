package org.medihub.application.ports.incoming.scheduling;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public interface ScheduleAppointmentUseCase {
    void scheduleAppointment(ScheduleAppointmentCommand command) throws NotFoundException, ForbiddenException, NotAvailableException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class ScheduleAppointmentCommand extends SelfValidating<ScheduleAppointmentCommand> {
        @NotNull
        Long doctorId;
        @NotNull
        LocalDate date;
        @NotNull
        LocalTime time;
        @NotBlank
        String type;

        public ScheduleAppointmentCommand(
                Long doctorId,
                LocalDate date,
                LocalTime time,
                String type) {
            this.doctorId = doctorId;
            this.date = date;
            this.time = time;
            this.type = type;
            this.validateSelf();
        }
    }
}
