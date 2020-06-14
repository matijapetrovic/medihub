package org.medihub.application.ports.outgoing.clinic_room;

import org.medihub.application.exceptions.AlreadyExistException;
import org.medihub.domain.clinic_room.ClinicRoom;

public interface SaveClinicRoomPort {
    void saveClinicRoom(ClinicRoom clinicRoom) throws AlreadyExistException;
}
