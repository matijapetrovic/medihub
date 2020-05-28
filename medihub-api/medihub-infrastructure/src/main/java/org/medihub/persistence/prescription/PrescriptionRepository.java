package org.medihub.persistence.prescription;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<PrescriptionJpaEntity, Long> {
}
