package org.medihub.persistence.medical_doctor_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.medical_doctor.MedicalDoctorSchedule;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType;
import org.medihub.domain.scheduling.DailySchedule;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MedicalDoctorScheduleAdapter {
    private final MedicalDoctorScheduleRepository repository;
    private final MedicalDoctorScheduleItemRepository itemRepository;

    public MedicalDoctorSchedule loadMedicalDoctorSchedule(Long doctorId) {
        Set<MedicalDoctorScheduleJpaEntity> schedules = repository
                .findAllByDoctor_Id(doctorId);

        Map<LocalDate, DailySchedule<MedicalDoctorScheduleItem>> dailySchedules =
            schedules
                .stream()
                .collect(Collectors.toMap(
                        schedule -> schedule.getDate().toLocalDate(),
                        schedule -> loadMedicalDoctorDailySchedule(schedule.getId())
                ));

        return new MedicalDoctorSchedule(dailySchedules);
    }

    private DailySchedule<MedicalDoctorScheduleItem> loadMedicalDoctorDailySchedule(Long scheduleId) {
        Set<MedicalDoctorScheduleItemJpaEntity> scheduleItems = itemRepository
                .findAllBySchedule_Id(scheduleId);

        return new DailySchedule<>(
                scheduleId,
                scheduleItems
                        .stream()
                        .map(scheduleItem -> new MedicalDoctorScheduleItem(
                            scheduleItem.getId(),
                            scheduleItem.getTime().toLocalTime(),
                            MedicalDoctorScheduleItemType.values()[scheduleItem.getType() - 1]))
                        .collect(Collectors.toSet()));
    }
}
