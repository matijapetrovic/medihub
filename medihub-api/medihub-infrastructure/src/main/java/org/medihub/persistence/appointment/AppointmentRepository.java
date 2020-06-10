package org.medihub.persistence.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<AppointmentJpaEntity, Long> {
    Optional<AppointmentJpaEntity> findById(Long id);
    List<AppointmentJpaEntity> findAllByDoctorIdAndPatientId(Long doctorId, Long patientId);


    List<AppointmentJpaEntity> findAllByDoctorClinicId(Long clinicId);

    @Query("select app from AppointmentJpaEntity app where app.patient.id=:patient_id and app not in " +
            "(select fapp.appointment from FinishedAppointmentJpaEntity fapp)")
    List<AppointmentJpaEntity> findAllScheduledByPatientId(@Param("patient_id") Long patientId);
}
