package org.medihub.application.ports.incoming.clinic_room;

import org.medihub.domain.clinic_room.ClinicRoom;

import java.util.List;

public interface GetAllClinicRoomsUseCase {
    List<ClinicRoom> getAll();
}
