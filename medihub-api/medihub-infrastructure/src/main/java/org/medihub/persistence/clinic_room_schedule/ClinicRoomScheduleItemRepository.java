package org.medihub.persistence.clinic_room_schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.Set;

public interface ClinicRoomScheduleItemRepository extends JpaRepository<ClinicRoomScheduleItemJpaEntity, Long> {
    Set<ClinicRoomScheduleItemJpaEntity> findAllBySchedule_Id(Long scheduleId);
    Optional<ClinicRoomScheduleItemJpaEntity> findByTime(Time time);
}
