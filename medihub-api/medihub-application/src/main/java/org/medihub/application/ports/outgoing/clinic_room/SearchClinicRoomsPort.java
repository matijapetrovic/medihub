package org.medihub.application.ports.outgoing.clinic_room;

import org.medihub.domain.clinic_room.ClinicRoom;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SearchClinicRoomsPort {
    List<ClinicRoom> searchClinicRooms(String name, Integer number, LocalDate date, LocalTime time, Long clinicId);
}
