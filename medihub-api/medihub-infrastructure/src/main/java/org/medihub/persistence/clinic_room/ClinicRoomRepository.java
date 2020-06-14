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
    List<ClinicRoomJpaEntity> findAllByDeletedIsFalse();

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
            "and (cr.number=:number or :number is null ) " +
            "and (:timestamp not in (select crsi2.startTime from ClinicRoomScheduleItemJpaEntity crsi2 " +
            "where crsi2.clinicRoom = cr)) " +
            "and cr.clinic.id=:clinic_id " +
            "and cr.deleted=false " +
            "and ((:dateStart is null and :dateEnd is null) or 24 > (select count(crsi3) from ClinicRoomScheduleItemJpaEntity crsi3 " +
            "where crsi3.startTime between :dateStart and :dateEnd and crsi3.clinicRoom=cr ))")
    List<ClinicRoomJpaEntity> findAllWithNameOrNumberOnDateTime(
            @Param(value="name")String name,
            @Param(value="number") Integer number,
            @Param(value="timestamp") Timestamp timestamp,
            @Param(value="clinic_id") Long clinicId,
            @Param(value="dateStart") Timestamp dateStart,
            @Param(value="dateEnd") Timestamp dateEnd);

    @Query("select distinct cr " +
            "from ClinicRoomJpaEntity cr " +
            "where cr.clinic.id=:clinic_id " +
            "and ((:dateStart is null and :dateEnd is null) or 24 > (select count(crsi3) from ClinicRoomScheduleItemJpaEntity crsi3 " +
            "where crsi3.startTime between :dateStart and :dateEnd and crsi3.clinicRoom=cr ))")
    List<ClinicRoomJpaEntity> findAllWithNameOrNumberOnDate(
            @Param(value="clinic_id") Long clinicId,
            @Param(value="dateStart") Timestamp dateStart,
            @Param(value="dateEnd") Timestamp dateEnd);

    @Query("select count(ap) from AppointmentJpaEntity ap where ap.clinicRoom.id=:clinicRoomId " +
            "and ap.startTime > :now ")
    Long getCountOfScheduledClinicRooms(
            @Param(value="clinicRoomId") Long clinicRoomId,
            @Param(value="now") Timestamp now);
}