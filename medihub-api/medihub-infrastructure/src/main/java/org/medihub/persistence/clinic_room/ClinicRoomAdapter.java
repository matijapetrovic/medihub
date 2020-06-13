package org.medihub.persistence.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.outgoing.clinic_room.*;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.io.NotActiveException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        SearchClinicRoomsPort,
        GetAllClinicRoomsPort
{
    private final ClinicRoomMapper clinicRoomMapper;
    private final ClinicRoomRepository clinicRoomRepository;

    @Override
    public ClinicRoom loadClinicRoom(Long id) {
        ClinicRoomJpaEntity clinicRoomJpa =
                clinicRoomRepository
                        .findByIdAndDeletedIsFalse(id)
                        .orElseThrow(EntityNotFoundException::new);

        return clinicRoomMapper.mapToDomainEntity(clinicRoomJpa);
    }

    @Override
    public void saveClinicRoom(ClinicRoom clinicRoom) {
        clinicRoomRepository.save(clinicRoomMapper.mapToJpaEntity(clinicRoom));
    }

    @Override
    public void deleteClinicRoom(Long id) throws ForbiddenException {
        LocalDateTime now  = LocalDateTime.now();
        ClinicRoom clinicRoom = clinicRoomMapper.mapToDomainEntity(
                clinicRoomRepository.findByIdIfDeletedIsFalse(id, Timestamp.valueOf(now))
                        .orElseThrow(ForbiddenException::new));

        clinicRoom.setIsDeleted(true);
        clinicRoomRepository.save(clinicRoomMapper.mapToJpaEntity(clinicRoom));
    }

    @Override
    public List<ClinicRoom> getClinicRooms(Long clinicId) {
        return clinicRoomRepository
                .findAllByClinic_IdAndDeletedIsFalse(clinicId)
                .stream()
                .map(clinicRoomMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ClinicRoom getClinicRoom(Long id) throws NotFoundException {
        return clinicRoomMapper.mapToDomainEntity(clinicRoomRepository.findByIdAndDeletedIsFalse(id).orElseThrow(NotFoundException::new));
    }

    public ClinicRoom getClinicRoomById(Long id) throws NotFoundException {
        return clinicRoomMapper.mapToDomainEntity(clinicRoomRepository.findByIdAndDeletedIsFalse(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public List<ClinicRoom> searchClinicRooms(String name, Integer number, LocalDate date, LocalTime time, Long clinicId) {
        Timestamp timestamp = (date == null || time == null) ? null : Timestamp.valueOf(LocalDateTime.of(date, time));

        return clinicRoomRepository
                .findAllWithNameOrNumberOnDate(name, number, timestamp ,clinicId)
                .stream()
                .map(clinicRoomMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClinicRoom> getAll() {
        return clinicRoomMapper.mapToDomainList(clinicRoomRepository.findAllByDeletedIsFalse());
    }
}
