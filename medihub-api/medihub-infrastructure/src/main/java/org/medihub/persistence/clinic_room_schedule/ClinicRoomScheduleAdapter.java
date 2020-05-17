package org.medihub.persistence.clinic_room_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.clinic_room_schedule.LoadClinicRoomSchedulePort;
import org.medihub.application.ports.outgoing.clinic_room_schedule.ScheduleClinicRoomPort;
import org.medihub.domain.clinic_room.ClinicRoomSchedule;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.clinic_room.ClinicRoomRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicRoomScheduleAdapter implements LoadClinicRoomSchedulePort, ScheduleClinicRoomPort {
    private final ClinicRoomScheduleJpaRepository clinicRoomScheduleJpaRepository;
    private final ClinicRoomScheduleItemRepository clinicRoomScheduleItemRepository;
    private final ClinicRoomRepository clinicRoomRepository;

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
    @Override
    public void scheduleClinicRoom(Long id, LocalDate date, LocalTime time) {
        if(!isDateScheduled(Date.valueOf(date))){
            ClinicRoomScheduleJpaEntity crs = new ClinicRoomScheduleJpaEntity(
                    null,
                    clinicRoomRepository.findById(id).get(),
                    Date.valueOf(date)
            );
            clinicRoomScheduleJpaRepository.save(crs);
        }


        clinicRoomScheduleItemRepository.save(
                new ClinicRoomScheduleItemJpaEntity(null,
                        clinicRoomScheduleJpaRepository.findByDate(Date.valueOf(date)),
                        Time.valueOf(time)));
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

    private boolean isDateScheduled(Date date) {
        return clinicRoomScheduleJpaRepository.findByDate(date) != null;
    }
}
