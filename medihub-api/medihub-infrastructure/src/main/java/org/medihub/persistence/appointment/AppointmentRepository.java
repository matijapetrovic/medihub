package org.medihub.persistence.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<AppointmentJpaEntity, Long> {
    Optional<AppointmentJpaEntity> findById(Long id);
    List<AppointmentJpaEntity> findAllByDoctorClinicId(Long clinicId);
}
