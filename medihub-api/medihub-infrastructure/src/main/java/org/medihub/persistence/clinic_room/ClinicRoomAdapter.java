package org.medihub.persistence.clinic_room;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AlreadyExistException;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.outgoing.clinic_room.*;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
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
        GetAllClinicRoomsPort,
        GetClinicRoomFutureScheduleCountPort
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
    public void saveClinicRoom(ClinicRoom clinicRoom) throws AlreadyExistException {
        try{
            clinicRoomRepository.save(clinicRoomMapper.mapToJpaEntity(clinicRoom));
        } catch (Exception e) {
            throw new AlreadyExistException();
        }
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
        if (timestamp == null)
            return searchClinicRooms(name, number, date, clinicId);

        Timestamp dateStart = (date == null ? null : Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIDNIGHT)));
        Timestamp dateEnd = (date == null ? null :Timestamp.valueOf(LocalDateTime.of(date, LocalTime.of(23, 0))));
        return clinicRoomRepository
                .findAllWithNameOrNumberOnDateTime(name, number, timestamp ,clinicId, dateStart, dateEnd)
                .stream()
                .map(clinicRoomMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    private List<ClinicRoom> searchClinicRooms(String name, Integer number, LocalDate date, Long clinicId) {
        Timestamp dateStart = (date == null ? null : Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIDNIGHT)));
        Timestamp dateEnd = (date == null ? null :Timestamp.valueOf(LocalDateTime.of(date, LocalTime.of(23, 0))));
        return clinicRoomRepository
                .findAllWithNameOrNumberOnDate(clinicId, dateStart, dateEnd)
                .stream()
                .map(clinicRoomMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClinicRoom> getAll() {
        return clinicRoomMapper.mapToDomainList(clinicRoomRepository.findAllByDeletedIsFalse());
    }

    @Override
    public Long countClinicRoomSchedule(Long clinicRoomId) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        LocalDate dateNow = dateTimeNow.toLocalDate();
        Timestamp now = (dateNow == null ? null :Timestamp.valueOf(LocalDateTime.of(dateNow, LocalTime.of(23, 0))));
        return clinicRoomRepository.getCountOfScheduledClinicRooms(clinicRoomId, now);
    }
}
