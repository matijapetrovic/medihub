package org.medihub.domain.clinic_room;

import org.medihub.domain.scheduling.DailyScheduleItem;

import java.time.LocalTime;

public class ClinicRoomScheduleItem extends DailyScheduleItem {
    public ClinicRoomScheduleItem(Long id, LocalTime time) {
        super(id, time);
    }
}
