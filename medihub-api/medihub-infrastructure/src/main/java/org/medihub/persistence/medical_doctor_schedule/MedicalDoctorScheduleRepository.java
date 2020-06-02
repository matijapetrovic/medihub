package org.medihub.persistence.medical_doctor_schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.Optional;
import java.util.Set;

public interface MedicalDoctorScheduleRepository extends JpaRepository<MedicalDoctorScheduleJpaEntity, Long> {
    Set<MedicalDoctorScheduleJpaEntity> findAllByDoctor_Id(Long doctorId);
    Optional<MedicalDoctorScheduleJpaEntity> findByDateAndDoctor_Id(Date date, Long doctorId);
    Optional<MedicalDoctorScheduleJpaEntity> findByDate(Date date);
}
