package org.medihub.application.ports.incoming.appointment_request;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;
import java.util.Date;

public interface ScheduleAppointmentUseCase {
    void scheduleAppointment(ScheduleAppointmentCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class ScheduleAppointmentCommand extends SelfValidating<ScheduleAppointmentCommand> {
        @NotNull
        Long patientId;
        @NotNull
        Long doctorId;
        @NotNull
        Long appointmentTypeId;
        @NotNull
        Date date;

        public ScheduleAppointmentCommand(
                Long patientId,
                Long doctorId,
                Long appointmentTypeId,
                Date date) {
            this.patientId = patientId;
            this.doctorId = doctorId;
            this.appointmentTypeId = appointmentTypeId;
            this.date = date;
            this.validateSelf();
        }
    }
}
