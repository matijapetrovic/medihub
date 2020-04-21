package org.medihub.application.ports.incoming;

import org.medihub.domain.ClinicRoom;

public interface ClinicRoomUseCase {
    void addClinicRoom(ClinicRoom clinicRoom);

    void deleteClinicRoom(ClinicRoom clinicRoom);

    void updateClinicRoom(ClinicRoom clinicRoom);
}
