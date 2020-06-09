package org.medihub.application.ports.incoming.scheduling;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public interface ScheduleDoctorsAppointmentUseCase {
    void scheduleAppointment(ScheduleDoctorsAppointmentCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class ScheduleDoctorsAppointmentCommand extends SelfValidating<ScheduleDoctorsAppointmentUseCase.ScheduleDoctorsAppointmentCommand> {
        @NotNull
        Long patientId;
        @NotNull
        LocalDate date;
        @NotNull
        LocalTime time;

        public ScheduleDoctorsAppointmentCommand(
                Long patientId,
                LocalDate date,
                LocalTime time) {
            this.patientId = patientId;
            this.date = date;
            this.time = time;
            this.validateSelf();
        }
    }
}
