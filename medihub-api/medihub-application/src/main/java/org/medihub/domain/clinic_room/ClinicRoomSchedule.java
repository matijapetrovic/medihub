package org.medihub.domain.clinic_room;

import lombok.Getter;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.domain.scheduling.Schedule;

import java.time.LocalDate;
import java.util.Map;

@Getter
public class ClinicRoomSchedule extends Schedule<ClinicRoomScheduleItem> {

    public ClinicRoomSchedule(
            Map<LocalDate, DailySchedule<ClinicRoomScheduleItem>> dailySchedules){
        super(dailySchedules);
    }
}
