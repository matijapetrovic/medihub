package org.medihub.persistence.clinic_room_schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

public interface ClinicRoomScheduleItemRepository extends JpaRepository<ClinicRoomScheduleItemJpaEntity, Long> {
    Optional<ClinicRoomScheduleItemJpaEntity> findByClinicRoomIdAndStartTime(Long clinicRoomId, Timestamp startTime);

    Set<ClinicRoomScheduleItemJpaEntity> findAllByClinicRoomId(Long clinicRoomId);
    Set<ClinicRoomScheduleItemJpaEntity> findAllByClinicRoomIdAndStartTimeBetween(
            Long clinicRoomId, Timestamp start, Timestamp end);
}
