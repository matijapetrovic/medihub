package org.medihub.application.ports.outgoing.clinic_room_schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ScheduleClinicRoomPort {
    void scheduleClinicRoom(Long id, LocalDate date, LocalTime time);
}
