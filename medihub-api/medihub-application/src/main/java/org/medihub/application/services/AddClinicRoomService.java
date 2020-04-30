package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.AddClinicRoomUseCase;
import org.medihub.application.ports.outgoing.LoadClinicRoomPort;
import org.medihub.application.ports.outgoing.SaveClinicRoomPort;
import org.medihub.domain.ClinicRoom;

@RequiredArgsConstructor
public class AddClinicRoomService implements AddClinicRoomUseCase {
    private final SaveClinicRoomPort saveClinicRoomPort;
    private final LoadClinicRoomPort loadClinicRoomPort;

    @Override
    public void addClinicRoom(ClinicRoom clinicRoom) {
        saveClinicRoomPort.saveClinicRoom(clinicRoom);
    }

}
