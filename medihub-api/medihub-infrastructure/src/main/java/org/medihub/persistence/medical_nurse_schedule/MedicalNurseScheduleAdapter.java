package org.medihub.persistence.medical_nurse_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.medical_nurse.GetNurseSchedulePort;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.SaveMedicalNurseScheduleItemPort;
import org.medihub.domain.medical_doctor.MedicalDoctorSchedule;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.medical_nurse.MedicalNurse;
import org.medihub.domain.medical_nurse.MedicalNurseSchedule;
import org.medihub.domain.medical_nurse.MedicalNurseScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemJpaEntity;
import org.medihub.persistence.medical_nurse.MedicalNurseJpaEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MedicalNurseScheduleAdapter implements SaveMedicalNurseScheduleItemPort, GetNurseSchedulePort {
    private final MedicalNurseScheduleItemRepository medicalNurseScheduleItemRepository;
    private final MedicalNurseScheduMapper medicalNurseScheduMapper;

    @Override
    public void saveMedicalNurseScheduleItem(MedicalNurseScheduleItem item, MedicalNurse nurse, LocalDate date) {
        medicalNurseScheduleItemRepository.save(medicalNurseScheduMapper.mapToScheduleItemJpaEntity(item ,nurse, date));
    }

    @Override
    public MedicalNurseSchedule getSchedule(Long id) {
        return getNurseSchedule(id);
    }

    public MedicalNurseSchedule getNurseSchedule(Long nurseId) {
        Set<MedicalNurseLeaveScheduleJpaItem> scheduleItems = medicalNurseScheduleItemRepository
                .findAllByNurseId(nurseId);

        Map<LocalDate, DailySchedule<MedicalNurseScheduleItem>> dailySchedules =
                new HashMap<>();
        for (MedicalNurseLeaveScheduleJpaItem scheduleItem : scheduleItems) {
            LocalDate date = scheduleItem.getStartTime().toLocalDateTime().toLocalDate();
            DailySchedule<MedicalNurseScheduleItem> dailySchedule = dailySchedules.get(date);
            if (dailySchedule == null) {
                dailySchedule = new DailySchedule<>(null);
                dailySchedules.put(date, dailySchedule);
            }
            dailySchedule.addToSchedule(medicalNurseScheduMapper.mapToScheduleItemDomainEntity(scheduleItem));
        }

        return new MedicalNurseSchedule(dailySchedules);
    }
}
