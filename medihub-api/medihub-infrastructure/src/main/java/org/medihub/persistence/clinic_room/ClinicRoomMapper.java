package org.medihub.persistence.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.persistence.clinic.ClinicMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClinicRoomMapper {
    private final ClinicMapper clinicMapper;

    public ClinicRoom mapToDomainEntity(ClinicRoomJpaEntity clinicRoomJpaEntity){
        return new ClinicRoom(
                clinicRoomJpaEntity.getId(),
                clinicRoomJpaEntity.getName(),
                clinicMapper.mapToDomainEntity(clinicRoomJpaEntity.getClinic()));
    }

    public ClinicRoomJpaEntity mapToJpaEntity(ClinicRoom clinicRoom){
        return new ClinicRoomJpaEntity(
                clinicRoom.getId(),
                clinicRoom.getName(),
                clinicMapper.mapToJpaEntity(clinicRoom.getClinic()));
    }
}