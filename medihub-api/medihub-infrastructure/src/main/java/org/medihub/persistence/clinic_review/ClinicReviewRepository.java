package org.medihub.persistence.clinic_review;

import org.medihub.persistence.predefined_appointment.PredefinedAppointmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface ClinicReviewRepository extends JpaRepository<ClinicReviewJpaEntity, Long> {
    Optional<ClinicReviewJpaEntity> findByPatientIdAndClinicId(Long patientId, Long clinicId);
    List<ClinicReviewJpaEntity> findAllByPatientIdAndCanReviewTrue(Long patientId);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select cr from ClinicReviewJpaEntity cr where cr.id = :id")
    @QueryHints({@QueryHint(name="javax.persistence.lock.timeout", value = "0")})
    Optional<ClinicReviewJpaEntity> findByIdWithLock(Long id);
}
