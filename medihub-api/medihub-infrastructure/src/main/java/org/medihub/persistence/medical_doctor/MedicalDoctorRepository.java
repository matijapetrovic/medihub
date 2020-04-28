package org.medihub.persistence.medical_doctor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalDoctorRepository extends JpaRepository<MedicalDoctorJpaEntity, Long> {
    Optional<MedicalDoctorJpaEntity> findById(Long id);
}
