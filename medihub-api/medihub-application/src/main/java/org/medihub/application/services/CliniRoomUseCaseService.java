package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.ClinicRoomUseCase;
import org.medihub.application.ports.outgoing.LoadClinicRoomPort;
import org.medihub.application.ports.outgoing.SaveClinicRoomPort;
import org.medihub.domain.ClinicRoom;

@RequiredArgsConstructor
public class CliniRoomUseCaseService implements ClinicRoomUseCase {
    private final SaveClinicRoomPort saveClinicRoomPort;
    private final LoadClinicRoomPort loadClinicRoomPort;


    @Override
    public void addClinicRoom(ClinicRoom clinicRoom) {
        saveClinicRoomPort.saveClinicRoom(clinicRoom);
    }

    @Override
    public void deleteClinicRoom(ClinicRoom clinicRoom) {

    }

    @Override
    public void updateClinicRoom(ClinicRoom clinicRoom) {

    }
}
