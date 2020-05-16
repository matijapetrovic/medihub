package org.medihub.persistence.clinic_room_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.clinic_room_schedule.LoadClinicRoomSchedulePort;
import org.medihub.domain.clinic_room.ClinicRoomSchedule;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicRoomSchedulePortAdapter implements LoadClinicRoomSchedulePort {
    private final ClinicRoomScheduleJpaRepository clinicRoomScheduleJpaRepository;
    private final ClinicRoomScheduleItemRepository clinicRoomScheduleItemRepository;

    public ClinicRoomSchedule loadClinicRoomSchedule(Long clinicRoomId){
        Set<ClinicRoomScheduleJpaEntity> schedules =
                clinicRoomScheduleJpaRepository.findAllByClinicRoom_Id(clinicRoomId);

        Map<LocalDate, DailySchedule<ClinicRoomScheduleItem>> dailySchedules =
                schedules
                        .stream()
                        .collect(Collectors.toMap(
                                schedule -> schedule.getDate().toLocalDate(),
                                schedule -> loadClinicRoomDailySchedule(schedule.getId())
                        ));

        return new ClinicRoomSchedule(dailySchedules);
    }

    private DailySchedule<ClinicRoomScheduleItem> loadClinicRoomDailySchedule(Long scheduleId){
        Set<ClinicRoomScheduleItemJpaEntity> scheduleItems = clinicRoomScheduleItemRepository
                .findAllBySchedule_Id(scheduleId);

        return new DailySchedule<>(
                scheduleId,
                scheduleItems
                        .stream()
                        .map(scheduleItem -> new ClinicRoomScheduleItem(
                                scheduleItem.getId(),
                                scheduleItem.getTime().toLocalTime()))
                        .collect(Collectors.toSet()));

    }
}
