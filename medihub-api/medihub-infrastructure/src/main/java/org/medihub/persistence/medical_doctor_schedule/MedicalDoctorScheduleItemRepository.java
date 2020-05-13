package org.medihub.persistence.medical_doctor_schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MedicalDoctorScheduleItemRepository extends JpaRepository<MedicalDoctorScheduleItemJpaEntity, Long> {
    Set<MedicalDoctorScheduleItemJpaEntity> findAllBySchedule_Id(Long scheduleId);
}
