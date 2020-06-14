package org.medihub.persistence.medical_doctor_schedule.appointment_schedule_item;

import org.springframework.data.jpa.repository.JpaRepository;
import  java.util.Optional;

public interface MedicalDoctorAppointmentScheduleItemRepository extends JpaRepository<MedicalDoctorAppointmentScheduleJpaItem, Long> {
    void deleteByAppointmentId(Long appointmentId);
    void deleteAllByDoctorId(Long doctorId);
    void deleteAllByAppointmentId(Long id);
}
