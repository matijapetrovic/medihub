package org.medihub.persistence.personal_info;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalInfoRepository extends JpaRepository<PersonalInfoJpaEntity, Long> {
    Optional<PersonalInfoJpaEntity> findByAccountId(Long accountId);
}
