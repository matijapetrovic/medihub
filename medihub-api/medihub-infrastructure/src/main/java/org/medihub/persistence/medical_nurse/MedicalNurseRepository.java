package org.medihub.persistence.medical_nurse;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalNurseRepository extends JpaRepository<MedicalNurseJpaEntity, Long> {
    MedicalNurseJpaEntity findByAccount_Id(Long accountId);
}
