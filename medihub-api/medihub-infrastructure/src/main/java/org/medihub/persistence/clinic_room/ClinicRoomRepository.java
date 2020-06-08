package org.medihub.persistence.clinic_room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface ClinicRoomRepository extends JpaRepository<ClinicRoomJpaEntity, Long> {
    Optional<ClinicRoomJpaEntity> findByName(String name);

    @Transactional
    void deleteClinicRoomByName(String name);

    List<ClinicRoomJpaEntity> findAllByClinic_Id(Long clinicId);

    List<ClinicRoomJpaEntity> findAll();

    @Query("select distinct cr " +
            "from ClinicRoomJpaEntity cr left join ClinicRoomScheduleJpaEntity crs on cr=crs.clinicRoom " +
            "left join ClinicRoomScheduleItemJpaEntity crsi on crsi.schedule=crs " +
            "where (cr.name like concat('%', :name, '%') or :name is null ) "+
            "and (cr.number=:number or :number is null ) " +
            "and (crs.date=:date or :date is null or crs.date is null) " +
            "and (crsi.time<>:time or :time is null or crsi.time is null) " +
            "and cr.clinic.id=:clinic_id")
    List<ClinicRoomJpaEntity> findAllWithNameOrNumberOnDate(
            @Param(value="name")String name,
            @Param(value="number") Integer number,
            @Param(value="date") Date date,
            @Param(value="time") Time time,
            @Param(value="clinic_id") Long clinicId
            );
}