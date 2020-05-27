package org.medihub.persistence.medical_doctor_schedule;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
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

    public MedicalDoctorScheduleJpaEntity mapToScheduleJpaEntity(MedicalDoctor doctor, LocalDate date, boolean available){
        return new MedicalDoctorScheduleJpaEntity(
                null,
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

    public MedicalDoctorAppointmentScheduleJpaItem mapToScheduleItemJpaEntity(
            MedicalDoctorScheduleJpaEntity schedule,
            LocalTime time,
            Appointment appointment) {
        return new MedicalDoctorAppointmentScheduleJpaItem(
                null,
                schedule,
                Time.valueOf(time),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.APPOINTMENT.getOrdinal(),
                appointmentMapper.mapToJpaEntity(appointment)
        );
    }

}
