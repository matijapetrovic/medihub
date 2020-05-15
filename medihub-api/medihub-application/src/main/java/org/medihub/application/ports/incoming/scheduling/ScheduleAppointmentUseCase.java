package org.medihub.application.ports.incoming.scheduling;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public interface ScheduleAppointmentUseCase {
    void scheduleAppointment(ScheduleAppointmentCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class ScheduleAppointmentCommand extends SelfValidating<ScheduleAppointmentCommand> {
        @NotNull
        Long doctorId;
        @NotNull
        LocalDate date;
        @NotNull
        LocalTime time;

        public ScheduleAppointmentCommand(
                Long doctorId,
                LocalDate date,
                LocalTime time) {
            this.doctorId = doctorId;
            this.date = date;
            this.time = time;
            this.validateSelf();
        }
    }
}
