package org.medihub.application.services.clinic_room.delete;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.incoming.clinic_room.DeleteClinicRoomUseCase;
import org.medihub.application.ports.outgoing.clinic_room.DeleteClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.LoadClinicRoomPort;
import org.medihub.domain.clinic_room.ClinicRoom;

@RequiredArgsConstructor
public class DeleteClinicRoomService implements DeleteClinicRoomUseCase {
    private final DeleteClinicRoomPort deleteClinicRoomPort;
    private final LoadClinicRoomPort loadClinicRoomPort;

    @Override
    public void deleteClinicRoom(DeleteClinicCommand command) throws ForbiddenException, NotFoundException {
        ClinicRoom clinicRoom = loadClinicRoomPort.loadClinicRoom(command.getId());
        if (!clinicRoom.getClinic().getId().equals(command.getClinicId())) {
            throw new ForbiddenException();
        }
        deleteClinicRoomPort.deleteClinicRoom(clinicRoom.getId());
    }
}
