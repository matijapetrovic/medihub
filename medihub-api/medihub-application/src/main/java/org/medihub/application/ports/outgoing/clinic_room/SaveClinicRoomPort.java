package org.medihub.application.ports.outgoing.clinic_room;

import org.medihub.domain.ClinicRoom;

public interface SaveClinicRoomPort {
    void saveClinicRoom(ClinicRoom clinicRoom);
}