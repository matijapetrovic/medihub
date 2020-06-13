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
    List<MedicalDoctorJpaEntity> findAllByClinicIdAndArchivedFalse(Long clinicId);
    MedicalDoctorJpaEntity findByPersonalInfoAccountId(Long accountId);

    @Query("select d from MedicalDoctorJpaEntity d " +
            "where d.archived = false " +
            "and d.clinic = :clinic " +
            "and (:appointmentType is null or d.specialization = :appointmentType) " +
            "and ((:dateStart is null and :dateEnd is null) or d.working_hours > " +
            "       (select count(mdsi) from MedicalDoctorScheduleItemJpaEntity mdsi " +
            "        where mdsi.startTime between :dateStart and :dateEnd and mdsi.doctor=d)) ")
    Set<MedicalDoctorJpaEntity> findAllByClinicAndSpecializationAvailableOnDate(
            @Param(value="clinic") ClinicJpaEntity clinic,
            @Param(value="dateStart") Timestamp dateStart,
            @Param(value="dateEnd") Timestamp dateEnd,
            @Param(value="appointmentType") AppointmentTypeJpaEntity appointmentType);
}
