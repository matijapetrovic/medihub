package org.medihub.persistence.leave_request;


import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequestJpaEntity, Long> {
}
