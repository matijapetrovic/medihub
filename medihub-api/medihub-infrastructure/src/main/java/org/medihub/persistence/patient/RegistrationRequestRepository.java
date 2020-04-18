package org.medihub.persistence.patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequestJpaEntity, Long> {
}
