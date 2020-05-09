package org.medihub.application.ports.outgoing.clinic_room;

import org.medihub.domain.ClinicRoom;

import java.util.List;

public interface GetClinicRoomsPort {
    List<ClinicRoom> getClinicRooms(Long clinicId);
}
