package org.medihub.persistence.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.clinic_room.DeleteClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.GetClinicRoomsPort;
import org.medihub.application.ports.outgoing.clinic_room.LoadClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.SaveClinicRoomPort;
import org.medihub.domain.ClinicRoom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicRoomAdapter implements
        SaveClinicRoomPort,
        LoadClinicRoomPort,
        DeleteClinicRoomPort,
        GetClinicRoomsPort {
    private final ClinicRoomMapper clinicRoomMapper;
    private final ClinicRoomRepository clinicRoomRepository;

    @Override
    public ClinicRoom loadClinicRoom(Long id) {
        ClinicRoomJpaEntity clinicRoomJpa =
                clinicRoomRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);

        return clinicRoomMapper.mapToDomainEntity(clinicRoomJpa);
    }

    @Override
    public void saveClinicRoom(ClinicRoom clinicRoom) {
        clinicRoomRepository.save(clinicRoomMapper.mapToJpaEntity(clinicRoom));
    }

    @Override
    public void deleteClinicRoom(Long id) {
        clinicRoomRepository.deleteById(id);
    }

    @Override
    public List<ClinicRoom> getClinicRooms(Long clinicId) {
        return clinicRoomRepository
                .findAllByClinic_Id(clinicId)
                .stream()
                .map(clinicRoomMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
