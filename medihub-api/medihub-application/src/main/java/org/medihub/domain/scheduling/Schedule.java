package org.medihub.domain.scheduling;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import static java.time.temporal.ChronoUnit.HOURS;

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

    public LocalTime getFirstDailySchedule(LocalDate date){
        LocalTime currentTime = LocalTime.parse("00:00");
        if(dailySchedules.get(date) == null)
            return LocalTime.parse("00:00");
        
        for (int i = 0; i < dailySchedules.get(date).getScheduleItems().size(); i++) {
            if (dailySchedules.get(date).isAvailable(currentTime))
                return currentTime;
            currentTime = currentTime.plusHours(1l);
        }
        return currentTime;
    }
}
