package org.medihub.persistence.clinic_room_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.persistence.clinic_room.ClinicRoomMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class ClinicRoomScheduleMapper {
    private final ClinicRoomMapper clinicRoomMapper;

    public ClinicRoomScheduleJpaEntity mapToScheduleJpaEntity(ClinicRoom clinicRoom, LocalDate date){
        return new ClinicRoomScheduleJpaEntity(
                clinicRoom.getId(),
                clinicRoomMapper.mapToJpaEntity(clinicRoom),
                Date.valueOf(date)
        );
    }
}
