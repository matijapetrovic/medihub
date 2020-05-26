package org.medihub.persistence.predefined_appointment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PredefinedAppointmentRepository extends JpaRepository<PredefinedAppointmentJpaEntity, Long> {
}
