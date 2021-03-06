package org.medihub.persistence.medical_doctor_schedule.schedule_item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Set;

public interface MedicalDoctorScheduleItemRepository extends JpaRepository<MedicalDoctorScheduleItemJpaEntity, Long> {
    Set<MedicalDoctorScheduleItemJpaEntity> findAllByDoctorId(Long doctorId);

    Set<MedicalDoctorScheduleItemJpaEntity> findAllByDoctorIdAndStartTimeBetween(
            Long doctorId, Timestamp start, Timestamp end);

    Integer countAllByDoctorIdAndStartTimeAfter(Long doctorId, Timestamp timestamp);
}
