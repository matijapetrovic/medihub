package org.medihub.persistence.leave_request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequestJpaEntity, Long> {
    List<LeaveRequestJpaEntity> findAllByDoctorClinicId(Long clinicId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select lr from LeaveRequestJpaEntity lr where lr.id = :id")
    @QueryHints({@QueryHint(name="javax.persistence.lock.timeout", value = "0")})
    Optional<LeaveRequestJpaEntity> findByIdWithLock(@Param(value="id") Long id);
}
