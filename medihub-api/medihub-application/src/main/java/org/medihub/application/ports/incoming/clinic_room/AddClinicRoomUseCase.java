package org.medihub.application.ports.incoming.clinic_room;

import org.medihub.domain.ClinicRoom;

public interface AddClinicRoomUseCase {
    void addClinicRoom(ClinicRoom clinicRoom);
}
