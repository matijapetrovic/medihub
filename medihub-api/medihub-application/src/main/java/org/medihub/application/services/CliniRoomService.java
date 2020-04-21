package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.AddClinicRoom;
import org.medihub.application.ports.outgoing.SaveClinicPort;

@RequiredArgsConstructor
public class CliniRoomService implements AddClinicRoom {
    private final SaveClinicPort databasePort;
    @Override
    public void addClinicRoom() { databasePort.addClinicRoom("testClinicRoom"); }
}
