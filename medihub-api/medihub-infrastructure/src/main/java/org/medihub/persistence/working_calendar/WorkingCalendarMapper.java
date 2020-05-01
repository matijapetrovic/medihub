package org.medihub.persistence.working_calendar;

import org.medihub.domain.WorkingCalendar;

public class WorkingCalendarMapper {

    public WorkingCalendar mapToDomainEntity(WorkingCalendarJpaEntity workingCalendarJpaEntity){
        return new WorkingCalendar();
    }

    public WorkingCalendarJpaEntity mapToJpaEntity(WorkingCalendar workingCalendar){
        return new WorkingCalendarJpaEntity();
    }
}
