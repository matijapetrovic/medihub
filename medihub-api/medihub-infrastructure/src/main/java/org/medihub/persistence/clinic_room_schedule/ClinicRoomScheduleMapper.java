package org.medihub.persistence.clinic_room_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.clinic_room.ClinicRoomSchedule;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.clinic_room.ClinicRoomMapper;
import org.medihub.persistence.medical_doctor_schedule.MedicalDoctorScheduleItemJpaEntity;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicRoomScheduleMapper {
    private final ClinicRoomMapper clinicRoomMapper;

    public ClinicRoomSchedule mapToDomainEntity(ClinicRoomScheduleJpaEntity clinicRoomScheduleJpaEntity){
        return new ClinicRoomSchedule(
                clinicRoomScheduleJpaEntity.getId(),
                mapToDailyScheduleMap(clinicRoomScheduleJpaEntity.getScheduleItems()),
                clinicRoomMapper.mapToDomainEntity(clinicRoomScheduleJpaEntity.clinicRoomJpaEntity)

        );
    }

    public ClinicRoomScheduleJpaEntity mapToJpaEntity(ClinicRoomSchedule clinicRoomSchedule){
        return new ClinicRoomScheduleJpaEntity(
                clinicRoomSchedule.getId(),
                clinicRoomMapper.mapToJpaEntity(clinicRoomSchedule.getClinicRoom()),
                mapToScheduleItemJpaSet(clinicRoomSchedule.getDailySchedules())
                );
    }

    private Set<ClinicRoomScheduleItemJpaEntity> mapToScheduleItemJpaSet(
            Map<LocalDate, DailySchedule<ClinicRoomScheduleItem>> dailySchedules){
        Set<ClinicRoomScheduleItemJpaEntity> set = new HashSet<>();
        dailySchedules.forEach(
                ((localDate, dailySchedule) -> set.addAll(
                        dailySchedule
                                .getScheduleItems()
                                .stream()
                                .map(scheduleItem -> new ClinicRoomScheduleItemJpaEntity(
                                        scheduleItem.getId(),
                                        Date.valueOf(localDate),
                                        Time.valueOf(scheduleItem.getTime())
                                )).collect(Collectors.toSet())

                ))
        );
        return set;
    }

    private Map<LocalDate, DailySchedule<ClinicRoomScheduleItem>> mapToDailyScheduleMap(
            Set<ClinicRoomScheduleItemJpaEntity> scheduleItems) {
        var map = scheduleItems.
                stream().
                collect(Collectors.toMap(
                        scheduleItem -> scheduleItem.getDate().toLocalDate(),
                        scheduleItem -> new DailySchedule<ClinicRoomScheduleItem>()));
        scheduleItems.forEach(scheduleItem ->
                map
                    .get(scheduleItem.getDate().toLocalDate())
                    .addToSchedule(new ClinicRoomScheduleItem(
                            scheduleItem.getId(),
                            scheduleItem.getTime().toLocalTime())));
        return map;
    }
}
