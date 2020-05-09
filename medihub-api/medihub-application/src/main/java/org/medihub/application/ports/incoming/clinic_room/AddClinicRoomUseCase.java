package org.medihub.application.ports.incoming.clinic_room;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface AddClinicRoomUseCase {
    void addClinicRoom(AddClinicRoomCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddClinicRoomCommand extends SelfValidating<AddClinicRoomCommand> {
        @NotNull
        Long clinicId;
        @NotBlank
        String name;
    }
}
