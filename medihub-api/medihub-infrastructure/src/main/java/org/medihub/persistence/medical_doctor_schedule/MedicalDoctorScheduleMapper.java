package org.medihub.persistence.medical_doctor_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.medical_doctor.*;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.appointment.AppointmentMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item.MedicalDoctorAppointmentScheduleJpaItem;
import org.medihub.persistence.medical_doctor_schedule.predefined_appointment_schedule_item.MedicalDoctorPredefinedAppointmentScheduleJpaItem;
import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemJpaEntity;
import org.medihub.persistence.medical_doctor_schedule.vacation_schedule_item.MedicalDoctorVacationScheduleJpaItem;
import org.medihub.persistence.predefined_appointment.PredefinedAppointmentMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class MedicalDoctorScheduleMapper {
    private final MedicalDoctorMapper medicalDoctorMapper;
    private final AppointmentMapper appointmentMapper;
    private final PredefinedAppointmentMapper predefinedAppointmentMapper;

    public MedicalDoctorScheduleJpaEntity mapToScheduleJpaEntity(
            DailySchedule<MedicalDoctorScheduleItem> dailySchedule,
            MedicalDoctor doctor,
            LocalDate date,
            boolean available){
        return new MedicalDoctorScheduleJpaEntity(
                dailySchedule.getId(),
                medicalDoctorMapper.mapToJpaEntity(doctor),
                Date.valueOf(date),
                available
        );
    }

    public MedicalDoctorVacationScheduleJpaItem mapToJpaVacationItem(
            MedicalDoctorScheduleJpaEntity schedule,
            Time time,
            Integer type,
            Date endDate
    ) {
        return new MedicalDoctorVacationScheduleJpaItem(
                null,
                new MedicalDoctorScheduleJpaEntity(
                        schedule.getId(),
                        schedule.getDoctor(),
                        schedule.getDate(),
                        schedule.isAvailable()),
                time,
                type,
                endDate

        );
    }

    public MedicalDoctorScheduleItemJpaEntity mapToScheduleItemJpaEntity(
            MedicalDoctorScheduleJpaEntity doctorSchedule,
            MedicalDoctorScheduleItem scheduleItem) {
        switch(scheduleItem.getType()) {
            case OPERATION:
            case APPOINTMENT:
                MedicalDoctorAppointmentScheduleItem appointmentScheduleItem =
                        (MedicalDoctorAppointmentScheduleItem) scheduleItem;
                return new MedicalDoctorAppointmentScheduleJpaItem(
                        appointmentScheduleItem.getId(),
                        doctorSchedule,
                        Time.valueOf(appointmentScheduleItem.getTime()),
                        MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.APPOINTMENT.getOrdinal(),
                        appointmentMapper.mapToJpaEntity(appointmentScheduleItem.getAppointment()));
            case LEAVE:
            case VACATION:
                MedicalDoctorVacationScheduleItem vacationScheduleItem =
                        (MedicalDoctorVacationScheduleItem) scheduleItem;
                return new MedicalDoctorVacationScheduleJpaItem(
                        vacationScheduleItem.getId(),
                        doctorSchedule,
                        Time.valueOf(vacationScheduleItem.getTime()),
                        MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.VACATION.getOrdinal(),
                        Date.valueOf(vacationScheduleItem.getEndDate()));
            case PREDEFINED_APPOINTMENT:
                MedicalDoctorPredefinedAppointmentScheduleItem predefinedAppointmentScheduleItem =
                        (MedicalDoctorPredefinedAppointmentScheduleItem) scheduleItem;
                return new MedicalDoctorPredefinedAppointmentScheduleJpaItem(
                        predefinedAppointmentScheduleItem.getId(),
                        doctorSchedule,
                        Time.valueOf(predefinedAppointmentScheduleItem.getTime()),
                        MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.PREDEFINED_APPOINTMENT.getOrdinal(),
                        predefinedAppointmentMapper.mapToJpaEntity(predefinedAppointmentScheduleItem.getPredefinedAppointment())
                );
        }

        return null;
    }

}
