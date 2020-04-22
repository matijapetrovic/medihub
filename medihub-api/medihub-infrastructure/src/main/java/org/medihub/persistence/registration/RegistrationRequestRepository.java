package org.medihub.persistence.registration;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequestJpaEntity, Long> {
}
