package org.medihub.application.services.clinic_room.update;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.UpdateClinicRoomUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.clinic_room.LoadClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.SaveClinicRoomPort;
import org.medihub.domain.clinic_room.ClinicRoom;

@RequiredArgsConstructor
public class UpdateClinicRoomService implements UpdateClinicRoomUseCase {
    private final LoadClinicRoomPort loadClinicRoomPort;
    private final SaveClinicRoomPort saveClinicRoomPort;

    @Override
    public void updateClinicRoom(UpdateClinicRoomCommand updateClinicRoomCommand) {
        ClinicRoom clinicRoom = loadClinicRoomPort.loadClinicRoom(updateClinicRoomCommand.getId());
        clinicRoom = updateClinicRoom(updateClinicRoomCommand, clinicRoom);
        saveClinicRoomPort.saveClinicRoom(clinicRoom);
    }

    private ClinicRoom updateClinicRoom(UpdateClinicRoomCommand updateClinicRoomCommand, ClinicRoom clinicRoom) {
        clinicRoom.setName(updateClinicRoomCommand.getName());
        clinicRoom.setNumber(updateClinicRoomCommand.getNumber());

        return clinicRoom;
    }
}
