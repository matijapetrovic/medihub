package org.medihub.persistence.patient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<PatientJpaEntity, Long> {
    List<PatientJpaEntity> findAll();
}
