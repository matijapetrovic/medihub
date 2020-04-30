package org.medihub.persistence;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.LoadClinicRoomPort;
import org.medihub.application.ports.outgoing.SaveClinicRoomPort;
import org.medihub.domain.ClinicRoom;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;
import org.medihub.persistence.clinic_room.ClinicRoomMapper;
import org.medihub.persistence.clinic_room.ClinicRoomRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class ClinicRoomRoomAdapter implements SaveClinicRoomPort, LoadClinicRoomPort {
    private final ClinicRoomMapper clinicRoomMapper;
    private final ClinicRoomRepository clinicRoomRepository;

    @Override
    public ClinicRoom loadClinicRoom(String name) {
        ClinicRoomJpaEntity clinicRoomJpa =
                clinicRoomRepository
                        .findByName(name)
                        .orElseThrow(EntityNotFoundException::new);

        return clinicRoomMapper.mapToDomainEntity(clinicRoomJpa);
    }

    @Override
    public void saveClinicRoom(ClinicRoom clinicRoom) {
        clinicRoomRepository.save(clinicRoomMapper.mapToJpaEntity(clinicRoom));
    }

}
