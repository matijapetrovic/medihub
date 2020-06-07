package org.medihub.persistence.medical_doctor_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorVacationScheduleItem;
import org.medihub.domain.scheduling.DailySchedule;
import org.medihub.persistence.appointment.AppointmentMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
@Component
public class MedicalDoctorScheduleMapper {
    private final MedicalDoctorMapper medicalDoctorMapper;
    private final AppointmentMapper appointmentMapper;

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
        }

        return null;
    }

}
