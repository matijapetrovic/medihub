package org.medihub.persistence.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.clinic_room.*;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicRoomAdapter implements
        SaveClinicRoomPort,
        LoadClinicRoomPort,
        DeleteClinicRoomPort,
        GetClinicRoomsPort,
        SearchClinicRoomsPort {
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

    @Override
    public ClinicRoom getClinicRoom(Long id) {
        return clinicRoomMapper.mapToDomainEntity(clinicRoomRepository.findById(id).get());
    }

    public ClinicRoom getClinicRoomById(Long id) {
        return clinicRoomMapper.mapToDomainEntity(clinicRoomRepository.findById(id).get());
    }

    @Override
    public List<ClinicRoom> searchClinicRooms(String name, Integer number, LocalDate date, LocalTime time, Long clinicId) {
        return clinicRoomRepository
                .findAllWithNameOrNumberOnDate(name, number, date != null? Date.valueOf(date) : null,
                        time != null? Time.valueOf(time) : null,clinicId)
                .stream()
                .map(clinicRoomMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

}
