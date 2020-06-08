package org.medihub.persistence.medical_doctor_schedule.predefined_appointment_schedule_item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalDoctorPredefinedAppointmentScheduleItemRepository
        extends JpaRepository<MedicalDoctorPredefinedAppointmentScheduleJpaItem, Long> {
}
