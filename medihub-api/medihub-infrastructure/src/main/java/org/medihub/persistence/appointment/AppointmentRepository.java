package org.medihub.persistence.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<AppointmentJpaEntity, Long> {
    Optional<AppointmentJpaEntity> findById(Long id);
}
