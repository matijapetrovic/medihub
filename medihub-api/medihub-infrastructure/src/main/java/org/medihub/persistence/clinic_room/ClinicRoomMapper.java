package org.medihub.persistence.clinic_room;

import org.medihub.domain.ClinicRoom;
import org.springframework.stereotype.Component;

@Component
public class ClinicRoomMapper {

    public ClinicRoom mapToDomainEntity(ClinicRoomJpaEntity clinicRoomJpaEntity){
        return new ClinicRoom(
                clinicRoomJpaEntity.getName());
    }

    public ClinicRoomJpaEntity mapToJpaEntity(ClinicRoom clinicRoom){
        return new ClinicRoomJpaEntity(
                null,
                clinicRoom.getName());
    }
}