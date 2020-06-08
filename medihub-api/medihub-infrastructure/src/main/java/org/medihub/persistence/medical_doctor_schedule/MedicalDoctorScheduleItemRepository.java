package org.medihub.persistence.medical_doctor_schedule;

import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

public interface MedicalDoctorScheduleItemRepository extends JpaRepository<MedicalDoctorScheduleItemJpaEntity, Long> {
    Set<MedicalDoctorScheduleItemJpaEntity> findAllByDoctorId(Long doctorId);

    Set<MedicalDoctorScheduleItemJpaEntity> findAllByDoctorIdAndStartTimeBetween(
            Long doctorId, Timestamp start, Timestamp end);
}
