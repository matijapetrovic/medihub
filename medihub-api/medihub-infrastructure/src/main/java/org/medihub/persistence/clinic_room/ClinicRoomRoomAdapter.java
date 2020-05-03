package org.medihub.persistence.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.DeleteClinicRoomPort;
import org.medihub.application.ports.outgoing.LoadClinicRoomPort;
import org.medihub.application.ports.outgoing.SaveClinicRoomPort;
import org.medihub.domain.ClinicRoom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class ClinicRoomRoomAdapter implements SaveClinicRoomPort, LoadClinicRoomPort, DeleteClinicRoomPort {
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

    @Override
    public void deleteClinicRoom(String clinicRoomName) {
        clinicRoomRepository.deleteClinicRoomByName(clinicRoomName);
    }
}
