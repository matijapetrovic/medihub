package org.medihub.persistence.appointment_request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequestJpaEntity, Long> {
    List<AppointmentRequestJpaEntity> findAllByDoctorClinicIdAndDoctorArchivedFalse(Long clinicId);
    List<AppointmentRequestJpaEntity> findAllByDoctorArchivedFalse();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select ar from AppointmentRequestJpaEntity ar where ar.id = :id")
    @QueryHints({@QueryHint(name="javax.persistence.lock.timeout", value = "0")})
    Optional<AppointmentRequestJpaEntity> findByIdWithLock(@Param(value = "id") Long id);
}
