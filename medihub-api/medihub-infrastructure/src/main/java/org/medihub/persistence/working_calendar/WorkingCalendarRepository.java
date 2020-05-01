package org.medihub.persistence.working_calendar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkingCalendarRepository extends JpaRepository<WorkingCalendarJpaEntity, Long> {
    Optional<WorkingCalendarJpaEntity> findById(Long id);
}
