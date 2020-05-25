package org.medihub.persistence.medical_doctor_schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.Optional;

public interface MedicalDoctorVacationScheduleItemRepository extends JpaRepository<MedicalDoctorVacationScheduleJpaItem, Long> {
    Optional<MedicalDoctorVacationScheduleJpaItem> findByTimeAndAndScheduleId(Time time, Long ScheduleId);
}
