package org.medihub.persistence.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AbstractAppointmentRepository extends JpaRepository<AbstractAppointmentJpaEntity, Long> {
    Optional<AbstractAppointmentJpaEntity> findById(Long id);
    List<AbstractAppointmentJpaEntity> findAllByDoctorClinicId(Long clinicId);

    @Query("select app from AbstractAppointmentJpaEntity app where app.patient.id=:patient_id and app not in " +
            "(select fapp.appointment from FinishedAppointmentJpaEntity fapp)")
    List<AbstractAppointmentJpaEntity> findAllScheduledByPatientId(@Param("patient_id") Long patientId);

    @Query("select app from AppointmentJpaEntity app " +
            "where app.doctor.id = :doctorId and app.patient.id=:patientId " +
            "   and app.startTime > :start and app.startTime <= :end " +
            "   and app not in (select fa.appointment from FinishedAppointmentJpaEntity fa)")
    Optional<AppointmentJpaEntity> findCurrentAppointment(
            @Param(value="patientId") Long patientId,
            @Param(value="doctorId") Long doctorId,
            @Param(value="start") Timestamp start,
            @Param(value="end") Timestamp end);
}
