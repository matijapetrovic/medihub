package org.medihub.application.services;

import org.medihub.application.ports.incoming.ManageClinicRooms;
import org.medihub.application.ports.outgoing.DatabasePort;

public class ClinicRoomService implements ManageClinicRooms {
    private final DatabasePort databasePort;
    @Override
    public void addClinicRoom() { databasePort.addClinicRoom("testClinicRoom"); }
}
