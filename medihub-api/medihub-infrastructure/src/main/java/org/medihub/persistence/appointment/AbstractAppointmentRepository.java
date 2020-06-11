package org.medihub.persistence.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AbstractAppointmentRepository extends JpaRepository<AbstractAppointmentJpaEntity, Long> {
    Optional<AbstractAppointmentJpaEntity> findById(Long id);
    List<AbstractAppointmentJpaEntity> findAllByDoctorClinicId(Long clinicId);

    @Query("select app from AbstractAppointmentJpaEntity app where app.patient.id=:patient_id and app not in " +
            "(select fapp.appointment from FinishedAppointmentJpaEntity fapp)")
    List<AbstractAppointmentJpaEntity> findAllScheduledByPatientId(@Param("patient_id") Long patientId);
}
