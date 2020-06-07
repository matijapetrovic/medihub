package org.medihub.persistence.clinic_room_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.clinic_room.ClinicRoomSchedule;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.clinic_room.ClinicRoomMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class ClinicRoomScheduleMapper {
    private final ClinicRoomMapper clinicRoomMapper;

    public ClinicRoomScheduleJpaEntity mapToScheduleJpaEntity(
            DailySchedule<ClinicRoomScheduleItem> dailySchedule,
            ClinicRoom clinicRoom,
            LocalDate date){
        return new ClinicRoomScheduleJpaEntity(
                dailySchedule.getId(),
                clinicRoomMapper.mapToJpaEntity(clinicRoom),
                Date.valueOf(date)
        );
    }

    public ClinicRoomScheduleItemJpaEntity mapToScheduleItemJpaEntity(
        ClinicRoomScheduleJpaEntity clinicRoomSchedule,
        ClinicRoomScheduleItem scheduleItem
    ) {
        return new ClinicRoomScheduleItemJpaEntity(
                scheduleItem.getId(),
                clinicRoomSchedule,
                Time.valueOf(scheduleItem.getTime()));
    }

    public ClinicRoomScheduleItem mapToScheduleItemDomainEntity(ClinicRoomScheduleItemJpaEntity scheduleItem) {
        return new ClinicRoomScheduleItem(
                scheduleItem.getId(),
                scheduleItem.getTime().toLocalTime());
    }
}
