package org.medihub.persistence.medical_doctor_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.medical_doctor.*;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.appointment.AppointmentMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item.MedicalDoctorAppointmentScheduleJpaItem;
import org.medihub.persistence.medical_doctor_schedule.predefined_appointment_schedule_item.MedicalDoctorPredefinedAppointmentScheduleJpaItem;
import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemJpaEntity;
import org.medihub.persistence.medical_doctor_schedule.vacation_schedule_item.MedicalDoctorVacationScheduleJpaItem;
import org.medihub.persistence.predefined_appointment.PredefinedAppointmentMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MedicalDoctorScheduleMapper {
    private final MedicalDoctorMapper medicalDoctorMapper;
    private final AppointmentMapper appointmentMapper;
    private final PredefinedAppointmentMapper predefinedAppointmentMapper;

    public Set<MedicalDoctorScheduleItemJpaEntity> mapToScheduleJpaEntity(
            DailySchedule<MedicalDoctorScheduleItem> dailySchedule,
            MedicalDoctor doctor,
            LocalDate date){
        return dailySchedule
                .getScheduleItems()
                .stream()
                .map(scheduleItem -> mapToScheduleItemJpaEntity(scheduleItem, doctor, date))
                .collect(Collectors.toSet());
    }

    public DailySchedule<MedicalDoctorScheduleItem> mapToScheduleDomainEntity(
            Set<MedicalDoctorScheduleItemJpaEntity> scheduleItemJpaEntities) {
        Set<MedicalDoctorScheduleItem> scheduleItems =
                scheduleItemJpaEntities
                        .stream()
                        .map(this::mapToScheduleItemDomainEntity)
                        .collect(Collectors.toSet());

        return new DailySchedule<>(null, scheduleItems);
    }

    public MedicalDoctorScheduleItemJpaEntity mapToScheduleItemJpaEntity(
            MedicalDoctorScheduleItem scheduleItem,
            MedicalDoctor doctor,
            LocalDate date) {
        switch(scheduleItem.getType()) {
            case OPERATION:
            case APPOINTMENT:
                MedicalDoctorAppointmentScheduleItem appointmentScheduleItem =
                        (MedicalDoctorAppointmentScheduleItem) scheduleItem;
                return new MedicalDoctorAppointmentScheduleJpaItem(
                        appointmentScheduleItem.getId(),
                        medicalDoctorMapper.mapToJpaEntity(doctor),
                        Timestamp.valueOf(LocalDateTime.of(date, scheduleItem.getTime())),
                        MedicalDoctorScheduleItemType.APPOINTMENT.getOrdinal(),
                        appointmentMapper.mapToJpaEntity(appointmentScheduleItem.getAppointment()));
            case LEAVE:
            case VACATION:
                MedicalDoctorVacationScheduleItem vacationScheduleItem =
                        (MedicalDoctorVacationScheduleItem) scheduleItem;
                return new MedicalDoctorVacationScheduleJpaItem(
                        vacationScheduleItem.getId(),
                        medicalDoctorMapper.mapToJpaEntity(doctor),
                        Timestamp.valueOf(LocalDateTime.of(date, scheduleItem.getTime())),
                        MedicalDoctorScheduleItemType.VACATION.getOrdinal(),
                        Timestamp.valueOf(LocalDateTime.of(vacationScheduleItem.getEndDate(), LocalTime.MIDNIGHT)));
            case PREDEFINED_APPOINTMENT:
                MedicalDoctorPredefinedAppointmentScheduleItem predefinedAppointmentScheduleItem =
                        (MedicalDoctorPredefinedAppointmentScheduleItem) scheduleItem;
                return new MedicalDoctorPredefinedAppointmentScheduleJpaItem(
                        predefinedAppointmentScheduleItem.getId(),
                        medicalDoctorMapper.mapToJpaEntity(doctor),
                        Timestamp.valueOf(LocalDateTime.of(date, scheduleItem.getTime())),
                        MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.PREDEFINED_APPOINTMENT.getOrdinal(),
                        predefinedAppointmentMapper.mapToJpaEntity(predefinedAppointmentScheduleItem.getPredefinedAppointment())
                );
        }

        return null;
    }

    public MedicalDoctorScheduleItem mapToScheduleItemDomainEntity(
            MedicalDoctorScheduleItemJpaEntity scheduleItem) {
        MedicalDoctorScheduleItemType type =
                MedicalDoctorScheduleItemType.valueOf(scheduleItem.getType())
                .orElseThrow();
        switch(type) {
            case OPERATION:
            case APPOINTMENT:
                MedicalDoctorAppointmentScheduleJpaItem appointmentScheduleItem =
                        (MedicalDoctorAppointmentScheduleJpaItem) scheduleItem;
                return new MedicalDoctorAppointmentScheduleItem(
                        appointmentScheduleItem.getId(),
                        appointmentScheduleItem.getStartTime().toLocalDateTime().toLocalTime(),
                        MedicalDoctorScheduleItemType.APPOINTMENT,
                        appointmentMapper.mapToDomainEntity(appointmentScheduleItem.getAppointment()));
            case LEAVE:
            case VACATION:
                MedicalDoctorVacationScheduleJpaItem vacationScheduleItem
                        = (MedicalDoctorVacationScheduleJpaItem) scheduleItem;
                return new MedicalDoctorVacationScheduleItem(
                        vacationScheduleItem.getId(),
                        vacationScheduleItem.getStartTime().toLocalDateTime().toLocalTime(),
                        MedicalDoctorScheduleItemType.VACATION,
                        vacationScheduleItem.getEndDate().toLocalDateTime().toLocalDate());
            case PREDEFINED_APPOINTMENT:
                MedicalDoctorPredefinedAppointmentScheduleJpaItem predefinedAppointmentScheduleItem =
                        (MedicalDoctorPredefinedAppointmentScheduleJpaItem) scheduleItem;
                return new MedicalDoctorPredefinedAppointmentScheduleItem(
                    predefinedAppointmentScheduleItem.getId(),
                    predefinedAppointmentScheduleItem.getStartTime().toLocalDateTime().toLocalTime(),
                    MedicalDoctorScheduleItemType.PREDEFINED_APPOINTMENT,
                    predefinedAppointmentMapper.mapToDomainEntity(predefinedAppointmentScheduleItem.getPredefinedAppointment())
                );
        }

        return null;
    }

}
