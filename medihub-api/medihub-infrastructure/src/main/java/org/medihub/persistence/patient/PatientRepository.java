package org.medihub.persistence.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientJpaEntity, Long> {
    List<PatientJpaEntity> findAll();
    Optional<PatientJpaEntity> findByAccount_Id(Long accountId);

    @Query("select distinct fa.appointment.patient from FinishedAppointmentJpaEntity fa " +
    "where fa.appointment.doctor.id = :doctor_id")
    List<PatientJpaEntity> getPreviousPatients(@Param("doctor_id") Long doctorId);
}
