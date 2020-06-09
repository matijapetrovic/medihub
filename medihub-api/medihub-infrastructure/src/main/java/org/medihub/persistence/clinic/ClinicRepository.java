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
    @Query("select d.clinic " +
            "from MedicalDoctorJpaEntity d " +
            "where (:appointmentType is null or " +
            "   (d.specialization = :appointmentType " +
            "   and d.clinic in (select c from ClinicJpaEntity c join c.appointmentTypePrices atp " +
            "       where KEY(atp)=:appointmentType)))" +
            "and ((:dateStart is null and :dateEnd is null) or " +
            "   (0 = (select count(mdvsi) from MedicalDoctorVacationScheduleJpaItem mdvsi " +
            "               where mdvsi.doctor = d and :dateStart between mdvsi.startTime and mdvsi.endDate)" +
            "    and d.working_hours > (select count(mdsi) from MedicalDoctorScheduleItemJpaEntity mdsi " +
            "                           where mdsi.startTime between :dateStart and :dateEnd" +
            "                               and mdsi.doctor=d))) ")
    Set<ClinicJpaEntity> findAllWithDoctorsByAppointmentTypeOnDate(
            @Param(value="dateStart") Timestamp dateStart,
            @Param(value="dateEnd") Timestamp dateEnd,
            @Param(value="appointmentType") AppointmentTypeJpaEntity appointmentType);
}
