package org.medihub.persistence.medical_doctor_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.appointment.GetAppointmentPort;
import org.medihub.application.ports.outgoing.doctor.AddAppointmentToMedicalDoctorSchedulePort;
import org.medihub.application.ports.outgoing.doctor.DeleteAppointmentScheduleItemPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorSchedulePort;
import org.medihub.application.ports.outgoing.leave_request.ApproveLeaveRequestPort;
import org.medihub.application.ports.outgoing.scheduling.LoadDoctorDailySchedulePort;
import org.medihub.application.ports.outgoing.scheduling.SaveDoctorDailySchedulePort;
import org.medihub.domain.LeaveRequest;
import org.medihub.domain.medical_doctor.*;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.appointment.AppointmentMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorRepository;
import org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item.MedicalDoctorAppointmentScheduleItemRepository;
import org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item.MedicalDoctorAppointmentScheduleJpaItem;
import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemJpaEntity;
import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemRepository;
import org.medihub.persistence.medical_doctor_schedule.vacation_schedule_item.MedicalDoctorVacationScheduleItemRepository;
import org.medihub.persistence.medical_doctor_schedule.vacation_schedule_item.MedicalDoctorVacationScheduleJpaItem;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MedicalDoctorScheduleAdapter implements
        LoadDoctorDailySchedulePort,
        SaveDoctorDailySchedulePort,
        GetDoctorSchedulePort,
        AddAppointmentToMedicalDoctorSchedulePort,
        ApproveLeaveRequestPort,
        DeleteAppointmentScheduleItemPort {
    private final AppointmentMapper appointmentMapper;
    private final GetAppointmentPort getAppointmentPort;

    private final MedicalDoctorScheduleMapper medicalDoctorScheduleMapper;

    private final MedicalDoctorRepository medicalDoctorRepository;
    private final MedicalDoctorScheduleRepository medicalDoctorScheduleRepository;
    private final MedicalDoctorScheduleItemRepository scheduleItemRepository;
    private final MedicalDoctorAppointmentScheduleItemRepository appointmentScheduleItemRepository;
    private final MedicalDoctorVacationScheduleItemRepository vacationScheduleItemRepository;

    public MedicalDoctorSchedule loadMedicalDoctorSchedule(Long doctorId) {
        Set<MedicalDoctorScheduleJpaEntity> schedules = medicalDoctorScheduleRepository
                .findAllByDoctor_Id(doctorId);

        Map<LocalDate, DailySchedule<MedicalDoctorScheduleItem>> dailySchedules =
            schedules
                .stream()
                .collect(Collectors.toMap(
                        schedule -> schedule.getDate().toLocalDate(),
                        schedule -> loadMedicalDoctorDailySchedule(schedule.getId())
                ));

        return new MedicalDoctorSchedule(dailySchedules);
    }

    private DailySchedule<MedicalDoctorScheduleItem> loadMedicalDoctorDailySchedule(Long scheduleId) {
        Set<MedicalDoctorScheduleItemJpaEntity> scheduleItems = scheduleItemRepository
                .findAllBySchedule_Id(scheduleId);

        return new DailySchedule<>(
                scheduleId,
                scheduleItems
                        .stream()
                        .map(scheduleItem -> getItem(scheduleItem))
                        .collect(Collectors.toSet()));
    }

    private MedicalDoctorScheduleItem getItem(MedicalDoctorScheduleItemJpaEntity jpaItem) {

        MedicalDoctorScheduleItem item;
        MedicalDoctorScheduleItemType type = MedicalDoctorScheduleItemType.values()[jpaItem.getType() - 1];

        switch (type) {
            case APPOINTMENT:
                MedicalDoctorAppointmentScheduleJpaItem appointmentItem = (MedicalDoctorAppointmentScheduleJpaItem) jpaItem;
                return new MedicalDoctorAppointmentScheduleItem(
                        appointmentItem.getId(),
                        appointmentItem.getTime().toLocalTime(),
                        type,
                        appointmentMapper.mapToDomainEntity(appointmentItem.getAppointment()));
            case LEAVE:
            case VACATION:
                MedicalDoctorVacationScheduleJpaItem vacationItem = (MedicalDoctorVacationScheduleJpaItem) jpaItem;
                return new MedicalDoctorVacationScheduleItem(
                        vacationItem.getId(),
                        vacationItem.getTime().toLocalTime(),
                        type,
                        vacationItem.getEndDate().toLocalDate());
        }

        return null;
    }

    @Override
    public DailySchedule<MedicalDoctorScheduleItem> loadDailySchedule(Long doctorId, LocalDate date) {
        MedicalDoctorJpaEntity doctor =
                medicalDoctorRepository.findById(doctorId)
                        .orElseThrow(EntityNotFoundException::new);

        Optional<MedicalDoctorScheduleJpaEntity> schedule =
                medicalDoctorScheduleRepository.findByDateAndDoctor_Id(Date.valueOf(date), doctorId);

        if (schedule.isEmpty())
            return new DailySchedule<>(null);

        return loadMedicalDoctorDailySchedule(schedule.get().getId());
    }

    @Override
    public MedicalDoctorSchedule getDoctorSchedule(Long doctorId) {
        return loadMedicalDoctorSchedule(doctorId);
    }

    @Override
    public void addAppointmentToSchedule(
                                         MedicalDoctor doctor,
                                         LocalDate date,
                                         LocalTime time,
                                         Long appointmentId) {

        MedicalDoctorScheduleJpaEntity schedule = getMedicalDoctorScheduleJpaEntity(doctor, date);
        MedicalDoctorAppointmentScheduleJpaItem scheduleJpaItem =
                new MedicalDoctorAppointmentScheduleJpaItem(
                        doctor.getId(),
                        schedule,
                        Time.valueOf(time),
                        MedicalDoctorScheduleItemType.APPOINTMENT.getOrdinal(),
                        appointmentMapper.mapToJpaEntity(getAppointmentPort.getAppointmentById(appointmentId)));

        medicalDoctorScheduleRepository.save(schedule);
        scheduleItemRepository.save(scheduleJpaItem);
    }

    private MedicalDoctorScheduleJpaEntity getMedicalDoctorScheduleJpaEntity(MedicalDoctor doctor, LocalDate date) {
        Optional<MedicalDoctorScheduleJpaEntity> schedule = medicalDoctorScheduleRepository.findByDate(Date.valueOf(date));
        if(!schedule.isPresent())
             return new MedicalDoctorScheduleJpaEntity(null, medicalDoctorRepository.findById(doctor.getId()).get(), Date.valueOf(date), false);
        return schedule.get();
    }

    private void saveIfDoesNotExist(MedicalDoctorVacationScheduleJpaItem item) {
        if(!(vacationScheduleItemRepository.findByAndEndDateAndAndScheduleId(
                item.getEndDate(),
                item.getSchedule().getId()).isPresent()))
        {
            vacationScheduleItemRepository.save(item);
        }
    }

    private List<LocalDate> fillDates(LocalDate start, LocalDate end) {
        List<LocalDate> dates = new ArrayList<>();
        while(!start.isAfter(end)) {
            dates.add(start);
            start = start.plusDays(1L);
        }
        return dates;
    }

    public void addLeave(List<String> dates, Integer type, MedicalDoctor medicalDoctor) {
        MedicalDoctorScheduleJpaEntity schedule = null;
        List<LocalDate> localDates = fillDates(LocalDate.parse(dates.get(0)), LocalDate.parse(dates.get(dates.size() - 1)));

        for (LocalDate date: localDates) {
            schedule = getMedicalDoctorScheduleJpaEntity(medicalDoctor, date);
            medicalDoctorScheduleRepository.save(schedule);
            if(date.equals(localDates.get(0))) {
                MedicalDoctorVacationScheduleJpaItem item = medicalDoctorScheduleMapper.mapToJpaVacationItem(
                        schedule,
                        Time.valueOf(LocalTime.parse("00:00")),
                        type,
                        Date.valueOf(dates.get(dates.size() - 1)));
                saveIfDoesNotExist(item);
            }
        }
    }

    @Override
    public void approveLeaveRequest(LeaveRequest leaveRequest, MedicalDoctor medicalDoctor) {
        addLeave(
                leaveRequest.getDates(),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(leaveRequest.getType()).getOrdinal(), medicalDoctor);
    }

    @Override
    public void deleteAppointmentItem(Long id) {
        appointmentScheduleItemRepository.deleteById(id);
    }

    @Override
    public void saveDoctorDailySchedule(MedicalDoctor doctor,
                                        LocalDate date,
                                        DailySchedule<MedicalDoctorScheduleItem> dailySchedule) {
        MedicalDoctorScheduleJpaEntity doctorSchedule =
                medicalDoctorScheduleMapper.mapToScheduleJpaEntity(
                        dailySchedule,
                        doctor,
                        date,
                        true);
        doctorSchedule = medicalDoctorScheduleRepository.save(doctorSchedule);

        for (MedicalDoctorScheduleItem scheduleItem : dailySchedule.getScheduleItems())
            saveScheduleItem(doctorSchedule, scheduleItem);
    }

    private void saveScheduleItem(MedicalDoctorScheduleJpaEntity doctorSchedule,
                                  MedicalDoctorScheduleItem scheduleItem) {
        MedicalDoctorScheduleItemJpaEntity scheduleItemJpaEntity
                = medicalDoctorScheduleMapper.mapToScheduleItemJpaEntity(
                        doctorSchedule, scheduleItem);

        scheduleItemRepository.save(scheduleItemJpaEntity);
    }
}
