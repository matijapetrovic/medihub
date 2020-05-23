package org.medihub.application.ports.outgoing.clinic_room;

import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.clinic_room.ClinicRoomSchedule;

import java.util.List;

public interface GetClinicRoomsPort {
    List<ClinicRoom> getClinicRooms(Long clinicId);

    ClinicRoom getClinicRoom(Long id);
    ClinicRoom getClinicRoomById(Long id);
}
