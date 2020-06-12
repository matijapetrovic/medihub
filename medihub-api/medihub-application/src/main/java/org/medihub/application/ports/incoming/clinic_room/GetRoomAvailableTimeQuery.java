package org.medihub.application.ports.incoming.clinic_room;

import java.time.LocalDate;
import java.util.List;

public interface GetRoomAvailableTimeQuery {
    List<String> loadClinicRoomDailySchedule(Long clinicRoomId, LocalDate date);
}
