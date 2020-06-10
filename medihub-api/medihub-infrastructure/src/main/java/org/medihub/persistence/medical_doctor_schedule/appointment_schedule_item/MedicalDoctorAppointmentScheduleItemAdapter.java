package org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.scheduling.schedule_item.LoadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort;
import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;
import org.medihub.domain.medical_doctor.MedicalDoctorScheduleItem;
import org.medihub.persistence.appointment.AppointmentMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class MedicalDoctorAppointmentScheduleItemAdapter
        implements LoadMedicalDoctorAppointmentScheduleItemByAppointmentIdPort {
    private final MedicalDoctorAppointmentScheduleItemRepository repository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public MedicalDoctorAppointmentScheduleItem loadById(Long id) {
        MedicalDoctorAppointmentScheduleJpaItem jpaItem =
                repository.findByAppointmentId(id);
        return new MedicalDoctorAppointmentScheduleItem(
                jpaItem.getId(),
                jpaItem.getStartTime().toLocalDateTime().toLocalTime(),
                MedicalDoctorScheduleItem.MedicalDoctorScheduleItemType.valueOf(jpaItem.getType()).get(),
                appointmentMapper.mapToDomainEntity(jpaItem.getAppointment())
        );
    }
}
