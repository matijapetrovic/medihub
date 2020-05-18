package org.medihub.persistence.finished_appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FinishedAppointmentRepository extends JpaRepository<FinishedAppointmentJpaEntity, Long> {
    Set<FinishedAppointmentJpaEntity> findAllByAppointment_Patient_Id(Long patientId);
}
