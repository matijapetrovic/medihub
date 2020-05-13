package org.medihub.persistence.medical_doctor_schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MedicalDoctorScheduleRepository extends JpaRepository<MedicalDoctorScheduleJpaEntity, Long> {
    Set<MedicalDoctorScheduleJpaEntity> findAllByDoctor_Id(Long doctorId);
}
