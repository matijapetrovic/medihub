package org.medihub.persistence.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountJpaEntity, Long> {
    Optional<AccountJpaEntity> findByEmail(String email);
}
