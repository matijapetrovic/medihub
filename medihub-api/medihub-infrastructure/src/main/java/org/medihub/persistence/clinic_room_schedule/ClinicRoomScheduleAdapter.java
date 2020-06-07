package org.medihub.persistence.clinic_room_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.clinic_room.AddAppointmentToClinicRoomPort;
import org.medihub.application.ports.outgoing.clinic_room_schedule.LoadClinicRoomSchedulePort;
import org.medihub.application.ports.outgoing.clinic_room_schedule.ScheduleClinicRoomPort;
import org.medihub.application.ports.outgoing.scheduling.LoadClinicRoomDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.SaveClinicRoomDailySchedulePort;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.clinic_room.ClinicRoomSchedule;
import org.medihub.domain.clinic_room.ClinicRoomScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;
import org.medihub.persistence.clinic_room.ClinicRoomRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicRoomScheduleAdapter implements
        LoadClinicRoomSchedulePort,
        ScheduleClinicRoomPort,
        AddAppointmentToClinicRoomPort,
        LoadClinicRoomDailySchedulePort,
        SaveClinicRoomDailySchedulePort {
    private final ClinicRoomScheduleJpaRepository clinicRoomScheduleJpaRepository;
    private final ClinicRoomScheduleItemRepository clinicRoomScheduleItemRepository;
    private final ClinicRoomScheduleMapper clinicRoomScheduleMapper;
    private final ClinicRoomRepository clinicRoomRepository;


    public ClinicRoomSchedule loadClinicRoomSchedule(Long clinicRoomId){
        Set<ClinicRoomScheduleJpaEntity> schedules =
                clinicRoomScheduleJpaRepository.findAllByClinicRoom_Id(clinicRoomId);

        Map<LocalDate, DailySchedule<ClinicRoomScheduleItem>> dailySchedules =
                schedules
                        .stream()
                        .collect(Collectors.toMap(
                                schedule -> schedule.getDate().toLocalDate(),
                                schedule -> loadClinicRoomDailySchedule(schedule.getId())
                        ));

        return new ClinicRoomSchedule(dailySchedules);
    }
    @Override
    public void scheduleClinicRoom(Long id, LocalDate date, LocalTime time) {
        if(!isDateScheduled(Date.valueOf(date))){
            ClinicRoomScheduleJpaEntity crs = new ClinicRoomScheduleJpaEntity(
                    null,
                    clinicRoomRepository.findById(id).get(),
                    Date.valueOf(date)
            );
            clinicRoomScheduleJpaRepository.save(crs);
        }


        clinicRoomScheduleItemRepository.save(
                new ClinicRoomScheduleItemJpaEntity(null,
                        clinicRoomScheduleJpaRepository.findByDate(Date.valueOf(date)),
                        Time.valueOf(time)));
    }

    private DailySchedule<ClinicRoomScheduleItem> loadClinicRoomDailySchedule(Long scheduleId){
        Set<ClinicRoomScheduleItemJpaEntity> scheduleItems = clinicRoomScheduleItemRepository
                .findAllBySchedule_Id(scheduleId);

        return new DailySchedule<>(
                scheduleId,
                scheduleItems
                        .stream()
                        .map(clinicRoomScheduleMapper::mapToScheduleItemDomainEntity)
                        .collect(Collectors.toSet()));

    }

    private boolean isDateScheduled(Date date) {
        return clinicRoomScheduleJpaRepository.findByDate(date) != null;
    }

    @Override
    public void addAppointmentToClinicRoom(
            ClinicRoom clinicRoom,
            LocalDate date,
            LocalTime time) {
        ClinicRoomScheduleJpaEntity schedule = new ClinicRoomScheduleJpaEntity(
                null,
                clinicRoomRepository.getOne(clinicRoom.getId()),
                Date.valueOf(date));
        ClinicRoomScheduleItemJpaEntity item = new ClinicRoomScheduleItemJpaEntity(null, schedule, Time.valueOf(time));

        clinicRoomScheduleJpaRepository.save(schedule);
        clinicRoomScheduleItemRepository.save(item);
    }

    @Override
    public DailySchedule<ClinicRoomScheduleItem> loadClinicRoomDailySchedule(Long clinicRoomId, LocalDate date) {
        ClinicRoomJpaEntity clinicRoom =
                clinicRoomRepository.findById(clinicRoomId)
                    .orElseThrow(EntityNotFoundException::new);

        Optional<ClinicRoomScheduleJpaEntity> schedule =
                clinicRoomScheduleJpaRepository.findByDateAndClinicRoom_Id(Date.valueOf(date), clinicRoomId);

        if (schedule.isEmpty())
            return new DailySchedule<>(null);

        return loadClinicRoomDailySchedule(schedule.get().getId());
    }

    @Override
    public void saveClinicRoomDailySchedule(ClinicRoom clinicRoom,
                                            LocalDate date,
                                            DailySchedule<ClinicRoomScheduleItem> dailySchedule) {
        ClinicRoomScheduleJpaEntity clinicRoomSchedule =
            clinicRoomScheduleMapper.mapToScheduleJpaEntity(
                    dailySchedule,
                    clinicRoom,
                    date);

        clinicRoomSchedule = clinicRoomScheduleJpaRepository.save(clinicRoomSchedule);

        for (ClinicRoomScheduleItem scheduleItem : dailySchedule.getScheduleItems()) {
            saveScheduleItem(clinicRoomSchedule, scheduleItem);
        }
    }

    private void saveScheduleItem(
            ClinicRoomScheduleJpaEntity clinicRoomSchedule,
            ClinicRoomScheduleItem scheduleItem) {
        ClinicRoomScheduleItemJpaEntity scheduleItemJpaEntity
                = clinicRoomScheduleMapper.mapToScheduleItemJpaEntity(
                        clinicRoomSchedule, scheduleItem);

        clinicRoomScheduleItemRepository.save(scheduleItemJpaEntity);
    }
}
