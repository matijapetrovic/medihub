package org.medihub.persistence.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PatientRepository extends JpaRepository<PatientJpaEntity, Long> {
    List<PatientJpaEntity> findAll();
}
