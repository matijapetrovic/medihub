package org.medihub.application.ports.incoming.clinic_room;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public interface ScheduleClinicRoomUseCase {
    void scheduleClinicRoom(ScheduleClinicRoomCommand command) throws NotFoundException, NotAvailableException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class ScheduleClinicRoomCommand extends SelfValidating<ScheduleClinicRoomUseCase.ScheduleClinicRoomCommand> {
        @NotNull
        Long id;
        @NotBlank
        LocalDate date;
        @NotBlank
        LocalTime time;

        public ScheduleClinicRoomCommand(
                Long id,
                LocalDate date,
                LocalTime time) {
            this.id = id;
            this.date = date;
            this.time = time;
        }
    }
}
