package org.medihub.domain.scheduling;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public class DailySchedule<T extends DailyScheduleItem> {
    private Long id;
    private Set<T> scheduleItems;

    public DailySchedule(Long id) {
        this.id = id;
        this.scheduleItems = new HashSet<>();
    }

    public boolean addToSchedule(T scheduleItem) {
        return scheduleItems.add(scheduleItem);
    }

    public boolean isAvailable(LocalTime time) {
        return scheduleItems
                .stream()
                .noneMatch(scheduleItem -> scheduleItem.getTime().equals(time));
    };
}
