package org.medihub.persistence.doctor_review;

import org.medihub.persistence.clinic_review.ClinicReviewJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface DoctorReviewRepository extends JpaRepository<DoctorReviewJpaEntity, Long> {
    Optional<DoctorReviewJpaEntity> findByPatientIdAndDoctorId(Long patientId, Long doctorId);
    List<DoctorReviewJpaEntity> findAllByPatientIdAndCanReviewTrue(Long patientId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select dr from DoctorReviewJpaEntity dr where dr.id = :id")
    @QueryHints({@QueryHint(name="javax.persistence.lock.timeout", value = "0")})
    Optional<DoctorReviewJpaEntity> findByIdWithLock(Long id);
}
