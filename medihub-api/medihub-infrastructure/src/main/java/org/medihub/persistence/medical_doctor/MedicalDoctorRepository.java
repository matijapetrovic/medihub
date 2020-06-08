package org.medihub.persistence.medical_doctor;

import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MedicalDoctorRepository extends JpaRepository<MedicalDoctorJpaEntity, Long> {
    Optional<MedicalDoctorJpaEntity> findById(Long id);

    List<MedicalDoctorJpaEntity> findAll();
    List<MedicalDoctorJpaEntity> findAllByClinicId(Long clinicId);
    MedicalDoctorJpaEntity findByAccountId(Long accountId);

    @Query("select d from MedicalDoctorJpaEntity d " +
            "where d.specialization = :appointmentType and d.clinic = :clinic " +
            "and d.working_hours > (select count(mdsi)" +
            "from MedicalDoctorJpaEntity dd inner join  MedicalDoctorScheduleItemJpaEntity mdsi on mdsi.doctor = dd " +
            "where mdsi.startTime=:timestamp and dd=d)")
    Set<MedicalDoctorJpaEntity> findAllByClinicAndSpecializationAvailableOnDate(
            @Param(value="clinic") ClinicJpaEntity clinic,
            @Param(value="timestamp") Timestamp timestamp,
            @Param(value="appointmentType") AppointmentTypeJpaEntity appointmentType);
}
