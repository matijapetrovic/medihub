package org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalDoctorAppointmentScheduleItemRepository extends JpaRepository<MedicalDoctorAppointmentScheduleJpaItem, Long> {
}
