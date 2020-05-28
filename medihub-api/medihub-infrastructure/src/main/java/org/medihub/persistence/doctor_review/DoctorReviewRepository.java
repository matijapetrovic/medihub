package org.medihub.persistence.doctor_review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorReviewRepository extends JpaRepository<DoctorReviewJpaEntity, Long> {
    Optional<DoctorReviewJpaEntity> findByAppointment_Id(Long appointmentId);

}
