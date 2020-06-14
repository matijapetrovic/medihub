package org.medihub.application.ports.incoming.clinic_room;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface UpdateClinicRoomUseCase {
    void updateClinicRoom(UpdateClinicRoomCommand updateClinicRoomCommand) throws ForbiddenException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class UpdateClinicRoomCommand extends SelfValidating<UpdateClinicRoomUseCase.UpdateClinicRoomCommand> {
        @NotNull
        Long id;
        @NotBlank
        String name;
        @NotNull
        int number;

        public UpdateClinicRoomCommand(
                Long id,
                String name,
                int number) {
            this.id = id;
            this.name = name;
            this.number = number;
            this.validateSelf();
        }
    }
}
