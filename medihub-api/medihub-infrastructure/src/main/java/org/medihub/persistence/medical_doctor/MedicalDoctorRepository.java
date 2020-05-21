package org.medihub.persistence.medical_doctor;

import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.medihub.persistence.clinic.ClinicJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MedicalDoctorRepository extends JpaRepository<MedicalDoctorJpaEntity, Long> {
    Optional<MedicalDoctorJpaEntity> findById(Long id);

    List<MedicalDoctorJpaEntity> findAll();
    List<MedicalDoctorJpaEntity> findAllByClinicId(Long clinicId);
    MedicalDoctorJpaEntity findByAccount_Id(Long accountId);

    @Query("select d from MedicalDoctorJpaEntity d " +
            "where d.specialization = :appointmentType and d.clinic = :clinic " +
            "and d.working_hours > (select count(mdsi) " +
            "from MedicalDoctorJpaEntity dd inner join MedicalDoctorScheduleJpaEntity mds on dd=mds.doctor " +
            "inner join MedicalDoctorScheduleItemJpaEntity mdsi on mdsi.schedule = mds " +
            "where mds.date=:date and dd=d)")
    Set<MedicalDoctorJpaEntity> findAllByClinicAndSpecializationAvailableOnDate(
            @Param(value="clinic") ClinicJpaEntity clinic,
            @Param(value="date") Date date,
            @Param(value="appointmentType") AppointmentTypeJpaEntity appointmentType);
}
