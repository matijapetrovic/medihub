package org.medihub.persistence.clinic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<ClinicJpaEntity, Long> {
}
