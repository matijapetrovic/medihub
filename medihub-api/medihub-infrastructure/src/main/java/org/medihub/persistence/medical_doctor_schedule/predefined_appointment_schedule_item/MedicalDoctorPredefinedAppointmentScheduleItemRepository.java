package org.medihub.persistence.medical_doctor_schedule.predefined_appointment_schedule_item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalDoctorPredefinedAppointmentScheduleItemRepository
        extends JpaRepository<MedicalDoctorPredefinedAppointmentScheduleJpaItem, Long> {
    Optional<MedicalDoctorPredefinedAppointmentScheduleJpaItem> findByPredefinedAppointmentId(Long predefinedAppointmentId);
}
