package org.medihub.persistence.leave_request;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequestJpaEntity, Long> {
    List<LeaveRequestJpaEntity> findAllByDoctorClinicId(Long clinicId);
}
