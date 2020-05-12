package org.medihub.persistence.clinic;

import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ClinicRepository extends JpaRepository<ClinicJpaEntity, Long> {
}
