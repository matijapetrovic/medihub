package org.medihub.application.ports.outgoing;

import org.medihub.domain.ClinicRoom;

public interface SaveClinicRoomPort {
    void saveClinicRoom(ClinicRoom clinicRoom);
}
