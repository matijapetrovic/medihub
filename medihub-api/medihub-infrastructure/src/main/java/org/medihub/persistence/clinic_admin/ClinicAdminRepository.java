package org.medihub.persistence.clinic_admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicAdminRepository extends JpaRepository<ClinicAdminJpaEntity, Long> {
    Optional<ClinicAdminJpaEntity> findByAccount_Id(Long accountId);
}
