package org.medihub.domain.scheduling;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
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

    public LocalDateTime getFirstDailySchedule(LocalDateTime datetime){
        LocalDate date = datetime.toLocalDate();
        LocalTime currentTime = datetime.toLocalTime();

        DailySchedule<T> dailySchedule = dailySchedules.get(date);

        if(dailySchedule == null)
            return datetime;

        for (LocalTime start = currentTime; start.isBefore(LocalTime.of(23, 0)); start = start.plusHours(1)) {
            if (dailySchedules.get(date).isAvailable(start))
                return LocalDateTime.of(date, start);
        }

        return getFirstFuture(date.plusDays(1));

    }

    private LocalDateTime getFirstFuture(LocalDate date) {
        LocalTime time = LocalTime.MIDNIGHT;
        DailySchedule<T> dailySchedule = dailySchedules.get(date);

        if(dailySchedule == null)
            return LocalDateTime.of(date, time);

        for (LocalTime start = time; start.isBefore(LocalTime.of(23, 0)); start = start.plusHours(1)) {
            if (dailySchedules.get(date).isAvailable(start))
                return LocalDateTime.of(date, start);
        }

        return getFirstFuture(date.plusDays(1));
    }
}
