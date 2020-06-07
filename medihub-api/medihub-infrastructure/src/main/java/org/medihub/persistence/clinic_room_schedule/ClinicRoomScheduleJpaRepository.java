package org.medihub.persistence.clinic_room_schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.Optional;
import java.util.Set;

public interface ClinicRoomScheduleJpaRepository  extends JpaRepository<ClinicRoomScheduleJpaEntity, Long> {
    Set<ClinicRoomScheduleJpaEntity> findAllByClinicRoom_Id (Long clinicRoomId);

    @Query("select crs " +
            "from ClinicRoomScheduleJpaEntity crs where crs.date=:date")
    ClinicRoomScheduleJpaEntity findByDate(@Param(value="date") Date date);

    Optional<ClinicRoomScheduleJpaEntity> findByDateAndClinicRoom_Id(Date date, Long ClinicRoomId);
    boolean existsByDate(Date date);
}
