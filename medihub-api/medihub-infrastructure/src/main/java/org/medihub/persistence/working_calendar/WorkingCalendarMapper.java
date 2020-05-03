package org.medihub.persistence.working_calendar;

import lombok.NoArgsConstructor;
import org.medihub.domain.WorkingCalendar;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class WorkingCalendarMapper {

    public WorkingCalendar mapToDomainEntity(WorkingCalendarJpaEntity workingCalendarJpaEntity){
        return new WorkingCalendar(
                workingCalendarJpaEntity.getId());
    }

    public WorkingCalendarJpaEntity mapToJpaEntity(WorkingCalendar workingCalendar){
        return new WorkingCalendarJpaEntity(null);
    }
}
