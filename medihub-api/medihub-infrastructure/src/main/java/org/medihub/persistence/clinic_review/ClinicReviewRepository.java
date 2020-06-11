package org.medihub.persistence.clinic_review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClinicReviewRepository extends JpaRepository<ClinicReviewJpaEntity, Long> {
    Optional<ClinicReviewJpaEntity> findByPatientIdAndClinicId(Long patientId, Long clinicId);
    List<ClinicReviewJpaEntity> findAllByPatientIdAndCanReviewTrue(Long patientId);
}
