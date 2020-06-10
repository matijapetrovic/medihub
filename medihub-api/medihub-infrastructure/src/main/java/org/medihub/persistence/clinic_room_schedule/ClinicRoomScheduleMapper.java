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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ClinicRoomScheduleMapper {
    private final ClinicRoomMapper clinicRoomMapper;

    public Set<ClinicRoomScheduleItemJpaEntity> mapToScheduleJpaEntity(
            DailySchedule<ClinicRoomScheduleItem> dailySchedule,
            ClinicRoom clinicRoom,
            LocalDate date){
        return dailySchedule
                .getScheduleItems()
                .stream()
                .map(scheduleItem -> mapToScheduleItemJpaEntity(clinicRoom, date, scheduleItem))
                .collect(Collectors.toSet());
    }

    public DailySchedule<ClinicRoomScheduleItem> mapToScheduleDomainEntity(
            Set<ClinicRoomScheduleItemJpaEntity> scheduleItemJpaEntities) {
        Set<ClinicRoomScheduleItem> scheduleItems =
                scheduleItemJpaEntities
                    .stream()
                    .map(this::mapToScheduleItemDomainEntity)
                    .collect(Collectors.toSet());
        return new DailySchedule<>(null, scheduleItems);
    }

    public ClinicRoomScheduleItemJpaEntity mapToScheduleItemJpaEntity(
            ClinicRoom clinicRoom,
            LocalDate date,
            ClinicRoomScheduleItem scheduleItem) {
        return new ClinicRoomScheduleItemJpaEntity(
                scheduleItem.getId(),
                clinicRoomMapper.mapToJpaEntity(clinicRoom),
                Timestamp.valueOf(LocalDateTime.of(date, scheduleItem.getTime())));
    }

    public ClinicRoomScheduleItem mapToScheduleItemDomainEntity(ClinicRoomScheduleItemJpaEntity scheduleItem) {
        return new ClinicRoomScheduleItem(
                scheduleItem.getId(),
                scheduleItem.getStartTime().toLocalDateTime().toLocalTime());
    }
}
