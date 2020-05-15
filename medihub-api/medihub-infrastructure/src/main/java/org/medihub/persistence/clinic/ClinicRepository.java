package org.medihub.persistence.clinic;

import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.Set;

public interface ClinicRepository extends JpaRepository<ClinicJpaEntity, Long> {
    @Query("select c " +
            "from MedicalDoctorJpaEntity d inner join ClinicJpaEntity c on d.clinic=c " +
            "where d.specialization = :appointmentType and d.working_hours > (select count(mdsi) " +
            "from MedicalDoctorJpaEntity dd inner join MedicalDoctorScheduleJpaEntity mds on dd=mds.doctor " +
            "inner join MedicalDoctorScheduleItemJpaEntity mdsi on mdsi.schedule = mds " +
            "where mds.date=:date and dd=d) " +
            "and c in (select c from ClinicJpaEntity c join c.appointmentTypePrices atp " +
            "           where KEY(atp) = :appointmentType)")
    Set<ClinicJpaEntity> findAllWithDoctorsByAppointmentTypeOnDate(
            @Param(value="date") Date date,
            @Param(value="appointmentType") AppointmentTypeJpaEntity appointmentType);
}
