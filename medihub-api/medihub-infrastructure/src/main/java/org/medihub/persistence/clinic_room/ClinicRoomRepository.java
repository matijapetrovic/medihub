package org.medihub.persistence.clinic_room;

import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClinicRoomRepository extends JpaRepository<ClinicRoomJpaEntity, Long> {
    Optional<ClinicRoomJpaEntity> findByName(String name);

    @Transactional
    void deleteClinicRoomByName(String name);

    List<ClinicRoomJpaEntity> findAllByClinic_Id(Long clinicId);

    List<ClinicRoomJpaEntity> findAll();

    @Query("select cr " +
            "from ClinicRoomJpaEntity cr inner join ClinicRoomScheduleJpaEntity crs on cr=crs.clinicRoom " +
            "where (cr.name like concat('%', :name, '%') or :name is null ) "+
            "and (cr.number=:number or :number is null ) " +
            "and (crs.date=:date or :date is null)"+
            "and cr.clinic.id=:clinic_id")
    List<ClinicRoomJpaEntity> findAllWithNameOrNumberOnDate(
            @Param(value="name")String name,
            @Param(value="number") Integer number,
            @Param(value="date") Date localDate,
            @Param(value="clinic_id") Long clinicId
            );
}