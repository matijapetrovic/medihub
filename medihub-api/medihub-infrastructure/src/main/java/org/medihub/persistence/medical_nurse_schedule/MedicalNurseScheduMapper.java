package org.medihub.persistence.medical_nurse_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.appointment.Operation;
import org.medihub.domain.medical_doctor.*;
import org.medihub.domain.medical_nurse.MedicalNurse;
import org.medihub.domain.medical_nurse.MedicalNurseScheduleItem;
import org.medihub.domain.medical_nurse.MedicalNurseVacationScheduleItem;
import org.medihub.persistence.medical_nurse.MedicalNurseMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MedicalNurseScheduMapper {
    private final MedicalNurseMapper medicalNurseMapper;

    public MedicalNurseLeaveScheduleJpaItem mapToScheduleItemJpaEntity(
            MedicalNurseScheduleItem scheduleItem,
            MedicalNurse nurse,
            LocalDate date) {
        MedicalNurseVacationScheduleItem item = (MedicalNurseVacationScheduleItem) scheduleItem;

        return new MedicalNurseLeaveScheduleJpaItem(
                item.getId(),
                medicalNurseMapper.mapToJpaEntity(nurse),
                Timestamp.valueOf(LocalDateTime.of(date, item.getTime())),
                item.getType().getOrdinal(),
                Timestamp.valueOf(LocalDateTime.of(item.getEndDate(), LocalTime.MIDNIGHT)));
    }

    public MedicalNurseScheduleItem mapToScheduleItemDomainEntity(
            MedicalNurseLeaveScheduleJpaItem scheduleItem) {

        MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType type =
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(scheduleItem.getType())
                        .orElseThrow();

        return new MedicalNurseVacationScheduleItem(
                scheduleItem.getId(),
                scheduleItem.getStartTime().toLocalDateTime().toLocalTime(),
                type,
                scheduleItem.getEndDate().toLocalDateTime().toLocalDate());
    }
}
