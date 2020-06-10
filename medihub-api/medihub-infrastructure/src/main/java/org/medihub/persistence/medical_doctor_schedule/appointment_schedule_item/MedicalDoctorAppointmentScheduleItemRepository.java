package org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Time;

public interface MedicalDoctorAppointmentScheduleItemRepository extends JpaRepository<MedicalDoctorAppointmentScheduleJpaItem, Long> {
    MedicalDoctorAppointmentScheduleJpaItem findByAppointmentId(Long id);
}
