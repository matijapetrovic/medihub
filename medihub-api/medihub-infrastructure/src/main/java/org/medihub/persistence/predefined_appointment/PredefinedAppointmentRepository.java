package org.medihub.persistence.predefined_appointment;

import org.medihub.persistence.appointment_request.AppointmentRequestJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface PredefinedAppointmentRepository extends JpaRepository<PredefinedAppointmentJpaEntity, Long> {
    List<PredefinedAppointmentJpaEntity> findAllByMedicalDoctor_Clinic_Id(Long clinicId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select ar from PredefinedAppointmentJpaEntity ar where ar.id = :id")
    @QueryHints({@QueryHint(name="javax.persistence.lock.timeout", value = "0")})
    Optional<PredefinedAppointmentJpaEntity> findByIdWithLock(@Param(value = "id") Long id);
}
