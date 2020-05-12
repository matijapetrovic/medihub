package org.medihub.domain.scheduling;

import lombok.Getter;

import java.time.LocalTime;
import java.util.Set;

public class DailySchedule<T extends DailyScheduleItem> {
    @Getter
    private Set<T> scheduleItems;

    public boolean addToSchedule(T scheduleItem) {
        return scheduleItems.add(scheduleItem);
    }

    public boolean isAvailable(LocalTime time) {
        return scheduleItems
                .stream()
                .noneMatch(scheduleItem -> scheduleItem.getTime().equals(time));
    };
}
