package org.medihub.persistence.clinic_room_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.clinic_room.AddAppointmentToClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room.LoadRoomDailySchedulePort;
import org.medihub.application.ports.outgoing.clinic_room_schedule.LoadClinicRoomSchedulePort;
import org.medihub.application.ports.outgoing.clinic_room_schedule.ScheduleClinicRoomPort;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.LoadClinicRoomDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.daily_schedule.SaveClinicRoomDailySchedulePort;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.clinic_room.ClinicRoomSchedule;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;
import org.medihub.persistence.clinic_room.ClinicRoomRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ClinicRoomScheduleAdapter implements
        LoadClinicRoomSchedulePort,
        ScheduleClinicRoomPort,
        AddAppointmentToClinicRoomPort,
        LoadClinicRoomDailySchedulePort,
        SaveClinicRoomDailySchedulePort,
        LoadRoomDailySchedulePort {
    private final ClinicRoomScheduleItemRepository itemRepository;
    private final ClinicRoomScheduleMapper clinicRoomScheduleMapper;
    private final ClinicRoomRepository clinicRoomRepository;

    @Override
    public ClinicRoomSchedule loadClinicRoomSchedule(Long clinicRoomId) {
        Set<ClinicRoomScheduleItemJpaEntity> scheduleItems =
                itemRepository.findAllByClinicRoomId(clinicRoomId);

        Map<LocalDate, DailySchedule<ClinicRoomScheduleItem>> dailySchedules = new HashMap<>();
        for (ClinicRoomScheduleItemJpaEntity scheduleItem: scheduleItems) {
            LocalDate date = scheduleItem.getStartTime().toLocalDateTime().toLocalDate();
            DailySchedule<ClinicRoomScheduleItem> dailySchedule = dailySchedules.get(date);
            if (dailySchedule == null) {
                dailySchedule = new DailySchedule<>(null);
                dailySchedules.put(date, dailySchedule);
            }
            dailySchedule.addToSchedule(clinicRoomScheduleMapper.mapToScheduleItemDomainEntity(scheduleItem));
        }

        return new ClinicRoomSchedule(dailySchedules);
    }

    @Override
    public void scheduleClinicRoom(Long id, LocalDate date, LocalTime time) throws NotAvailableException, NotFoundException {
        Timestamp startTime = Timestamp.valueOf(LocalDateTime.of(date, time));
        Optional<ClinicRoomJpaEntity> clinicRoom =
                clinicRoomRepository.findById(id);

        if (clinicRoom.isEmpty())
            throw new NotFoundException("Clinic room not found");

        Optional<ClinicRoomScheduleItemJpaEntity> scheduleItem =
                itemRepository.findByClinicRoomIdAndStartTime(id, startTime);

        if (scheduleItem.isPresent())
            throw new NotAvailableException("Clinic room not available");


        ClinicRoomScheduleItemJpaEntity toAdd =
                new ClinicRoomScheduleItemJpaEntity(null, clinicRoom.get(), startTime);
        itemRepository.save(toAdd);
    }

    @Override
    public void addAppointmentToClinicRoom(
            ClinicRoom clinicRoom,
            LocalDate date,
            LocalTime time) throws NotFoundException, NotAvailableException {
        scheduleClinicRoom(clinicRoom.getId(), date ,time);
    }

    @Override
    public DailySchedule<ClinicRoomScheduleItem> loadClinicRoomDailySchedule(Long clinicRoomId, LocalDate date) {
        ClinicRoomJpaEntity clinicRoom =
                clinicRoomRepository.findById(clinicRoomId)
                    .orElseThrow(EntityNotFoundException::new);

        Timestamp start = Timestamp.valueOf(LocalDateTime.of(date, LocalTime.MIDNIGHT));
        Timestamp end = Timestamp.valueOf(LocalDateTime.of(date.plusDays(1), LocalTime.MIDNIGHT));

        Set<ClinicRoomScheduleItemJpaEntity> scheduleJpaItems =
                itemRepository.findAllByClinicRoomIdAndStartTimeBetween(clinicRoomId, start, end);

        return clinicRoomScheduleMapper.mapToScheduleDomainEntity(scheduleJpaItems);
    }

    @Override
    public void saveClinicRoomDailySchedule(ClinicRoom clinicRoom,
                                            LocalDate date,
                                            DailySchedule<ClinicRoomScheduleItem> dailySchedule) {
       Set<ClinicRoomScheduleItemJpaEntity> clinicRoomDailySchedule =
               clinicRoomScheduleMapper.mapToScheduleJpaEntity(
                       dailySchedule,
                       clinicRoom,
                       date);
       itemRepository.saveAll(clinicRoomDailySchedule);
    }
}
