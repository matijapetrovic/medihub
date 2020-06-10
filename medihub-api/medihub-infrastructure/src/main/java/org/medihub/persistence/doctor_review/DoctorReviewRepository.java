package org.medihub.persistence.doctor_review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorReviewRepository extends JpaRepository<DoctorReviewJpaEntity, Long> {
    Optional<DoctorReviewJpaEntity> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
    List<DoctorReviewJpaEntity> findAllByPatientIdAndCanReviewTrue(Long patientId);
}
