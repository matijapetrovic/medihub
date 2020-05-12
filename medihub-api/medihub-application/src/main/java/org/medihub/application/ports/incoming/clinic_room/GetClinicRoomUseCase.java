package org.medihub.application.ports.incoming.clinic_room;

import org.medihub.domain.ClinicRoom;

import java.util.List;

public interface GetClinicRoomUseCase {
    List<ClinicRoom> getAll();
}
