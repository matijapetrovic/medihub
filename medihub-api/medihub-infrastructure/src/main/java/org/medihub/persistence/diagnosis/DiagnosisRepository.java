package org.medihub.persistence.diagnosis;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<DiagnosisJpaEntity, Long> {
}
