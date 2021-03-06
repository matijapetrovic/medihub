package org.medihub.application.ports.outgoing.clinic_room;

import org.medihub.application.exceptions.NotFoundException;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.clinic_room.ClinicRoomSchedule;

import java.io.NotActiveException;
import java.util.List;

public interface GetClinicRoomsPort {
    List<ClinicRoom> getClinicRooms(Long clinicId);

    ClinicRoom getClinicRoom(Long id) throws NotFoundException;
    ClinicRoom getClinicRoomById(Long id) throws NotFoundException;
}
