package org.medihub.application.ports.incoming.clinic_room;


import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;

public interface DeleteClinicRoomUseCase {
    void deleteClinicRoom(DeleteClinicCommand command) throws ForbiddenException, NotFoundException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class DeleteClinicCommand extends SelfValidating<DeleteClinicCommand> {
        @NotNull
        Long clinicId;
        @NotNull
        Long id;

        public DeleteClinicCommand(
                Long clinicId,
                Long id) {
            this.clinicId = clinicId;
            this.id = id;
            this.validateSelf();
        }
    }
}
