package org.medihub.persistence.clinic_review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicReviewRepository extends JpaRepository<ClinicReviewJpaEntity, Long> {
    Optional<ClinicReviewJpaEntity> findByAppointment_Id(Long appointmentId);
}
