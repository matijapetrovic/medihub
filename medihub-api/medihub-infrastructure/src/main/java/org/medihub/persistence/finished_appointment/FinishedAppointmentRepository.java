package org.medihub.persistence.finished_appointment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FinishedAppointmentRepository extends JpaRepository<FinishedAppointmentJpaEntity, Long> {
}
