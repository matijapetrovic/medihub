package org.medihub.persistence.medical_doctor_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.medical_doctor.MedicalDoctorSchedule;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
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
public class MedicalDoctorScheduleMapper {
    private final MedicalDoctorMapper medicalDoctorMapper;

    public MedicalDoctorScheduleJpaEntity mapToJpaEntity(MedicalDoctorSchedule schedule) {
        return new MedicalDoctorScheduleJpaEntity(
                schedule.getId(),
                medicalDoctorMapper.mapToJpaEntity(schedule.getDoctor()),
                mapToScheduleItemJpaSet(schedule.getDailySchedules()));
    }

    private Set<MedicalDoctorScheduleItemJpaEntity> mapToScheduleItemJpaSet(
            Map<LocalDate, DailySchedule<MedicalDoctorScheduleItem>> dailySchedules) {
        Set<MedicalDoctorScheduleItemJpaEntity> set = new HashSet<>();
        dailySchedules.forEach(
                (localDate, dailySchedule) -> set.addAll(
                        dailySchedule
                                .getScheduleItems()
                                .stream()
                                .map(scheduleItem -> new MedicalDoctorScheduleItemJpaEntity(
                                        scheduleItem.getId(),
                                        Date.valueOf(localDate),
                                        Time.valueOf(scheduleItem.getTime()),
                                        scheduleItem.getType().getOrdinal()))
                                .collect(Collectors.toSet())));
        return set;
    }

    public MedicalDoctorSchedule mapToDomainEntity(MedicalDoctorScheduleJpaEntity schedule) {
        return new MedicalDoctorSchedule(
                schedule.getId(),
                mapToDailyScheduleMap(schedule.getScheduleItems()),
                medicalDoctorMapper.mapToDomainEntity(schedule.getDoctorJpaEntity())
        );
    }

    private Map<LocalDate, DailySchedule<MedicalDoctorScheduleItem>> mapToDailyScheduleMap(
            Set<MedicalDoctorScheduleItemJpaEntity> scheduleItems) {
        var map = scheduleItems.
                stream().
                collect(Collectors.toMap(
                        scheduleItem -> scheduleItem.getDate().toLocalDate(),
                        scheduleItem -> new DailySchedule<MedicalDoctorScheduleItem>()));
        scheduleItems.forEach(scheduleItem ->
                map
                .get(scheduleItem.getDate().toLocalDate())
                .addToSchedule(new MedicalDoctorScheduleItem(
                        scheduleItem.getId(),
                        scheduleItem.getTime().toLocalTime(),
                        MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.values()[scheduleItem.getType() - 1])));
        return map;
    }
}
