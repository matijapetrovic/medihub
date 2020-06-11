package org.medihub.persistence.medical_doctor_schedule.operation_schedule_item;

import org.medihub.persistence.medical_doctor_schedule.schedule_item.MedicalDoctorScheduleItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalDoctorOperationScheduleRepository
        extends JpaRepository<MedicalDoctorOperationScheduleJpaItem, Long> {
}
