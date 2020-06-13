package org.medihub.persistence.leave_request;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseLeaveRequestRepository extends JpaRepository<NurseLeaveRequestJpaEntity, Long> {
}
