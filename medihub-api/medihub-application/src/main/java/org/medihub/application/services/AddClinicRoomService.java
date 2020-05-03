package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.clinic_room.AddClinicRoomUseCase;
import org.medihub.application.ports.outgoing.clinic_room.SaveClinicRoomPort;
import org.medihub.domain.ClinicRoom;

@RequiredArgsConstructor
public class AddClinicRoomService implements AddClinicRoomUseCase {
    private final SaveClinicRoomPort saveClinicRoomPort;

    @Override
    public void addClinicRoom(ClinicRoom clinicRoom) {
        saveClinicRoomPort.saveClinicRoom(clinicRoom);
    }
}
