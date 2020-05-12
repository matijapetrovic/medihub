package org.medihub.persistence.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.ClinicRoom;
import org.medihub.persistence.clinic.ClinicMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicRoomMapper {
    private final ClinicMapper clinicMapper;

    public ClinicRoom mapToDomainEntity(ClinicRoomJpaEntity clinicRoomJpaEntity){
        return new ClinicRoom(
                clinicRoomJpaEntity.getId(),
                clinicRoomJpaEntity.getName(),
                clinicRoomJpaEntity.getNumber(),
                clinicMapper.mapToDomainEntity(clinicRoomJpaEntity.getClinic()));
    }

    public ClinicRoomJpaEntity mapToJpaEntity(ClinicRoom clinicRoom){
        return new ClinicRoomJpaEntity(
                clinicRoom.getId(),
                clinicRoom.getName(),
                clinicRoom.getNumber(),
                clinicMapper.mapToJpaEntity(clinicRoom.getClinic()));
    }

    public List<ClinicRoom> mapToDomainList(List<ClinicRoomJpaEntity> clinicRoomJpaEntities){
        return clinicRoomJpaEntities
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}