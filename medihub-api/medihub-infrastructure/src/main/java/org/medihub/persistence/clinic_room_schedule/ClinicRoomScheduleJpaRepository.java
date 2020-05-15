package org.medihub.persistence.clinic_room_schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ClinicRoomScheduleJpaRepository  extends JpaRepository<ClinicRoomScheduleJpaEntity, Long> {
    Set<ClinicRoomScheduleJpaEntity> findAllByClinicRoom_Id (Long clinicRoomId);
}
