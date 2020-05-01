package org.medihub.application.ports.incoming;

import org.medihub.domain.ClinicRoom;

public interface AddClinicRoomUseCase {
    void addClinicRoom(ClinicRoom clinicRoom);
}
