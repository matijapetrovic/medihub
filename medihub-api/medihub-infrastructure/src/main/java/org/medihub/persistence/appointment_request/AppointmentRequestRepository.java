package org.medihub.persistence.appointment_request;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequestJpaEntity, Long> {
}
