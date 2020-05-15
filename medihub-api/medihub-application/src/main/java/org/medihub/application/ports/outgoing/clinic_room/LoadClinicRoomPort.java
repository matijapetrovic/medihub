package org.medihub.application.ports.outgoing.clinic_room;

import org.medihub.domain.clinic_room.ClinicRoom;

public interface LoadClinicRoomPort {
    ClinicRoom loadClinicRoom(Long id);
}
