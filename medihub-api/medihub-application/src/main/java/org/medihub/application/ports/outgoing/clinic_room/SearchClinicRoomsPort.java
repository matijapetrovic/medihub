package org.medihub.application.ports.outgoing.clinic_room;

import org.medihub.domain.clinic_room.ClinicRoom;

import java.time.LocalDate;
import java.util.List;

public interface SearchClinicRoomsPort {
    List<ClinicRoom> searchClinicRooms(String name, Integer number, LocalDate date, Long clinicId);
}
