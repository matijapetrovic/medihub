package org.medihub.persistence.clinic_admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClinicAdminRepository extends JpaRepository<ClinicAdminJpaEntity, Long> {
    Optional<ClinicAdminJpaEntity> findByPersonalInfoAccountId(Long accountId);

    List<ClinicAdminJpaEntity> findAllByClinicId(Long clinicId);
}
