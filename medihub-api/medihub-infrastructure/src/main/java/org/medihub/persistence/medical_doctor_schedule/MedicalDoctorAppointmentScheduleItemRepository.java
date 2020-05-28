package org.medihub.persistence.medical_doctor_schedule;

import org.medihub.domain.medical_doctor.MedicalDoctorAppointmentScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalDoctorAppointmentScheduleItemRepository extends JpaRepository<MedicalDoctorAppointmentScheduleJpaItem, Long> {
}
