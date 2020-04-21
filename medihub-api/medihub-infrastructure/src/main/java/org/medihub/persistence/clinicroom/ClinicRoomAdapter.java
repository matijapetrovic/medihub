package org.medihub.persistence;

import org.medihub.application.ports.outgoing.LoadClinicRoom;
import org.medihub.application.ports.outgoing.SaveClinicPort;
import org.medihub.domain.ClinicRoom;
import org.medihub.persistence.clinicroom.ClinicRoomJpaEntity;
import org.medihub.persistence.clinicroom.ClinicRoomMapper;
import org.medihub.persistence.clinicroom.ClinicRoomRespository;

import javax.persistence.EntityNotFoundException;

public class ClinicRoomAdapter implements SaveClinicPort, LoadClinicRoom {
    private final ClinicRoomMapper clinicRoomMapper;
    private final ClinicRoomRespository clinicRoomRepository;

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
