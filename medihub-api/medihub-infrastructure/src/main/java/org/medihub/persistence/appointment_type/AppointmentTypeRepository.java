package org.medihub.persistence.appointment_type;

import org.medihub.persistence.appointment.AppointmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentTypeJpaEntity, Long> {

    Optional<AppointmentTypeJpaEntity> findById(Long id);
}
