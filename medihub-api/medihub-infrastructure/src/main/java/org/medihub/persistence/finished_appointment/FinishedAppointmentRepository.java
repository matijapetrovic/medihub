package org.medihub.persistence.finished_appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface FinishedAppointmentRepository extends JpaRepository<FinishedAppointmentJpaEntity, Long> {
    List<FinishedAppointmentJpaEntity> findAllByAppointment_Patient_Id(Long patientId);
}
