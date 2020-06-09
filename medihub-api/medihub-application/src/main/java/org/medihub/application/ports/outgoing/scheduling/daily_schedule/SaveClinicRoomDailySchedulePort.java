package org.medihub.application.ports.outgoing.scheduling.daily_schedule;

import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;

import java.time.LocalDate;

public interface SaveClinicRoomDailySchedulePort {
    void saveClinicRoomDailySchedule(
            ClinicRoom clinicRoom,
            LocalDate date,
            DailySchedule<ClinicRoomScheduleItem> dailySchedule);
}
