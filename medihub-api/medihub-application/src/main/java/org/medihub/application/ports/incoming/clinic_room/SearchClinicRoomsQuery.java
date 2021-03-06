package org.medihub.application.ports.incoming.clinic_room;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SearchClinicRoomsQuery {
    List<SearchClinicRoomsOutput> searchClinicRooms(String name, Integer number, LocalDate date, LocalTime time);
}
