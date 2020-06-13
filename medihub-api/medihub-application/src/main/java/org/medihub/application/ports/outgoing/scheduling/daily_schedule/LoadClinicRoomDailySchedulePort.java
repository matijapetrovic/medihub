package org.medihub.application.ports.outgoing.scheduling.daily_schedule;

import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;

import java.time.LocalDate;

public interface LoadClinicRoomDailySchedulePort {
    DailySchedule<ClinicRoomScheduleItem> loadClinicRoomDailySchedule(Long clinicRoomId, LocalDate date);
}
