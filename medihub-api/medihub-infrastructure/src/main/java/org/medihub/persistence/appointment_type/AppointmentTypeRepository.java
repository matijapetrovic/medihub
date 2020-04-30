package org.medihub.persistence.appointment_type;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentTypeJpaEntity, Long> {
    Optional<AppointmentTypeJpaEntity> findByName(String name);
}
