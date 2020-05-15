package org.medihub.persistence.medical_record;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecordJpaEntity, Long> {
    Optional<MedicalRecordJpaEntity> findByPatient_Id(Long patientId);
}
