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

    public MedicalDoctorScheduleJpaEntity mapToScheduleJpaEntity(MedicalDoctor doctor, LocalDate date){
        return new MedicalDoctorScheduleJpaEntity(
                doctor.getId(),
                medicalDoctorMapper.mapToJpaEntity(doctor),
                Date.valueOf(date)
        );
    }

    public MedicalDoctorVacationScheduleJpaItem mapToJpaVacationItem(
            MedicalDoctorScheduleJpaEntity schedule,
            Time time,
            Integer type
    ) {
        return new MedicalDoctorVacationScheduleJpaItem(
                null,
                schedule,
                time,
                type
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
