
package org.medihub.persistence.medical_doctor_schedule.vacation_schedule_item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;

public interface MedicalDoctorVacationScheduleItemRepository extends JpaRepository<MedicalDoctorVacationScheduleJpaItem, Long> {
    Optional<MedicalDoctorVacationScheduleJpaItem> findByEndDateAndDoctorId(Date date, Long ScheduleId);
}
