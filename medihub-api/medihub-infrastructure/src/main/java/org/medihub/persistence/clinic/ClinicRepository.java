package org.medihub.persistence.clinic;

import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

public interface ClinicRepository extends JpaRepository<ClinicJpaEntity, Long> {
    @Query("select c " +
            "from MedicalDoctorJpaEntity d inner join ClinicJpaEntity c on d.clinic=c " +
            "where d.specialization = :appointmentType " +
            "and 0 = (select count(mdvsi) " +
            "from MedicalDoctorJpaEntity dd inner join MedicalDoctorVacationScheduleJpaItem mdvsi on mdvsi.doctor = dd " +
            "where dd = d and mdvsi.startTime <=:timestamp and mdvsi.endDate>=:timestamp)" +
            "and d.working_hours > (select count(mdsi) " +
            "from MedicalDoctorJpaEntity dd inner join  MedicalDoctorScheduleItemJpaEntity mdsi on mdsi.doctor = dd " +
            "where mdsi.startTime=:timestamp and dd=d) " +
            "and c in (select c from ClinicJpaEntity c join c.appointmentTypePrices atp " +
            "           where KEY(atp) = :appointmentType)")
    Set<ClinicJpaEntity> findAllWithDoctorsByAppointmentTypeOnDate(
            @Param(value="timestamp") Timestamp timestamp,
            @Param(value="appointmentType") AppointmentTypeJpaEntity appointmentType);
}
