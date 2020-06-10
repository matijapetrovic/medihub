package org.medihub.persistence.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;

public interface AppointmentRepository extends JpaRepository<AppointmentJpaEntity, Long> {
    Optional<AppointmentJpaEntity> findById(Long id);
    List<AppointmentJpaEntity> findAllByDoctorIdAndPatientId(Long doctorId, Long patientId);


    List<AppointmentJpaEntity> findAllByDoctorClinicId(Long clinicId);

    @Query("select app from AppointmentJpaEntity app where app.patient.id=:patient_id and app not in " +
            "(select fapp.appointment from FinishedAppointmentJpaEntity fapp)")
    List<AppointmentJpaEntity> findAllScheduledByPatientId(@Param("patient_id") Long patientId);

    // potencijalno startTime umesto time
    @Query("select app from AppointmentJpaEntity app " +
            "where app.doctor.id = :doctorId and app.patient.id=:patientId " +
            "   and app.time > :start and app.time <= :end " +
            "   and app not in (select fa.appointment from FinishedAppointmentJpaEntity fa)")
    Optional<AppointmentJpaEntity> findCurrentAppointment(
            @Param(value="patientId") Long patientId,
            @Param(value="doctorId") Long doctorId,
            @Param(value="start") Timestamp start,
            @Param(value="end") Timestamp end);
}
