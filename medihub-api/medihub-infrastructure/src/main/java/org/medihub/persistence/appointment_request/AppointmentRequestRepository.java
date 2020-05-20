package org.medihub.persistence.appointment_request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequestJpaEntity, Long> {
    @Query("select ar " +
            "from AppointmentRequestJpaEntity ar where ar.medicalDoctorJpaEntity.clinic.id=:clinicId")
    List<AppointmentRequestJpaEntity> findAll(@Param(value="clinicId") Long clinicId);
}
