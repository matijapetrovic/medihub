package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.AddClinicRoom;
import org.medihub.application.ports.outgoing.AddClinicRoomDataBasePort;

@RequiredArgsConstructor
public class CliniRoomService implements AddClinicRoom {
    private final AddClinicRoomDataBasePort databasePort;
    @Override
    public void addClinicRoom() { databasePort.addClinicRoom("testClinicRoom"); }
}
