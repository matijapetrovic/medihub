package org.medihub.persistence.clinic_room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ClinicRoomRepository extends JpaRepository<ClinicRoomJpaEntity, Long> {

    Optional<ClinicRoomJpaEntity> findByIdAndDeletedIsFalse(Long id);

    List<ClinicRoomJpaEntity> findAllByClinic_IdAndDeletedIsFalse(Long clinicId);
    List<ClinicRoomJpaEntity> findAll();

    @Query("select  cr from ClinicRoomJpaEntity cr where cr.id=:id and " +
            "cr not in (select a.clinicRoom from AppointmentJpaEntity a " +
            "where cr=a.clinicRoom and  a.startTime<:time)")
    Optional<ClinicRoomJpaEntity> findByIdIfDeletedIsFalse(
            @Param(value="id") Long id,
            @Param(value="time")Timestamp time);

    @Query("select distinct cr " +
            "from ClinicRoomJpaEntity cr left outer join ClinicRoomScheduleItemJpaEntity crsi on " +
            "cr=crsi.clinicRoom " +
            "where (cr.name like concat('%', :name, '%') or :name is null ) "+
            "and (cr.number=:number or :number is null )" +
            "and (crsi.startTime<>:timestamp or :timestamp is null or crsi.startTime is null) " +
            "and cr.clinic.id=:clinic_id")
    List<ClinicRoomJpaEntity> findAllWithNameOrNumberOnDate(
            @Param(value="name")String name,
            @Param(value="number") Integer number,
            @Param(value="timestamp") Timestamp timestamp,
            @Param(value="clinic_id") Long clinicId);
}