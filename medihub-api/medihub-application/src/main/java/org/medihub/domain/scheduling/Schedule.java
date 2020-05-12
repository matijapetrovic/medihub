package org.medihub.domain.scheduling;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Schedule<T extends DailyScheduleItem> {
    private Map<LocalDate, DailySchedule<T>> dailySchedules;

    public Schedule() {
        dailySchedules = new HashMap<>();
    }

    public Schedule(Map<LocalDate, DailySchedule<T>> dailySchedules) {
        this.dailySchedules = dailySchedules;
    }

    public boolean addToSchedule(LocalDate date, T scheduleItem) {
        DailySchedule<T> dailySchedule = dailySchedules.get(date);
        if (dailySchedule == null) {
            dailySchedule = new DailySchedule<>(null);
            dailySchedules.put(date, dailySchedule);
        }
        return dailySchedule.addToSchedule(scheduleItem);
    }

    public boolean isAvailable(LocalDate date, LocalTime time) {
        DailySchedule<T> dailySchedule = dailySchedules.get(date);
        if (dailySchedule == null)
            return true;
        return dailySchedule.isAvailable(time);
    }
}
