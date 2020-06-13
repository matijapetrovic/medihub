package org.medihub.persistence.medical_nurse_schedule;

import org.medihub.domain.medical_nurse.MedicalNurseScheduleItem;
import org.medihub.persistence.medical_doctor_schedule.vacation_schedule_item.MedicalDoctorVacationScheduleJpaItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MedicalNurseScheduleItemRepository extends JpaRepository<MedicalNurseLeaveScheduleJpaItem, Long> {
    public Set<MedicalNurseLeaveScheduleJpaItem> findAllByNurseId(Long id);
}
