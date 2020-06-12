package org.medihub.application.ports.outgoing.clinic_room;

import org.medihub.domain.clinic_room.ClinicRoom;

import java.util.List;

public interface GetAllClinicRoomsPort {
    List<ClinicRoom> getAll();
}
