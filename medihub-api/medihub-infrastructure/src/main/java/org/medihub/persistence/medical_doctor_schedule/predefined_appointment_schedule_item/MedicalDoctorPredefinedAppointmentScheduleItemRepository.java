package org.medihub.persistence.medical_doctor_schedule.predefined_appointment_schedule_item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface MedicalDoctorPredefinedAppointmentScheduleItemRepository
        extends JpaRepository<MedicalDoctorPredefinedAppointmentScheduleJpaItem, Long> {
    Optional<MedicalDoctorPredefinedAppointmentScheduleJpaItem> findByPredefinedAppointmentId(Long predefinedAppointmentId);
}
