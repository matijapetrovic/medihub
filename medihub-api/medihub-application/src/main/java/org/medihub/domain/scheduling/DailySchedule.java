package org.medihub.domain.scheduling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.WorkingTime;

import java.time.LocalTime;
import java.util.*;

@Getter
@AllArgsConstructor
public class DailySchedule<T extends DailyScheduleItem> {
    private Long id;
    private Set<T> scheduleItems;

    public DailySchedule(Long id) {
        this.id = id;
        this.scheduleItems = new HashSet<>();
    }

    public void addAllToSchedules(Set<T> scheduleItems) { this.scheduleItems.addAll(scheduleItems);}

    public boolean addToSchedule(T scheduleItem) {
        return scheduleItems.add(scheduleItem);
    }

    public boolean isAvailable(LocalTime time) {
        return scheduleItems
                .stream()
                .noneMatch(scheduleItem -> scheduleItem.getTime().equals(time));
    };

    public List<LocalTime> getAvailableTimes(WorkingTime workingTime) {
        if (workingTime.getFrom().isAfter(workingTime.getTo()))
            return getAvailableTimesNightShift(workingTime);
        return getAvailableTimesDayShift(workingTime);
    }

    private List<LocalTime> getAvailableTimesDayShift(WorkingTime workingTime) {
        List<LocalTime> availableTimes = new LinkedList<>();

        for (LocalTime currentTime = workingTime.getFrom();
            currentTime.isBefore(workingTime.getTo());
            currentTime = currentTime.plusHours(1)) {

            if (isAvailable(currentTime))
                availableTimes.add(currentTime);
        }
        return availableTimes;
    }

    private List<LocalTime> getAvailableTimesNightShift(WorkingTime workingTime) {
        List<LocalTime> availableTimes = new LinkedList<>();

        for (LocalTime currentTime = LocalTime.MIDNIGHT;
             currentTime.isBefore(workingTime.getTo());
             currentTime = currentTime.plusHours(1)) {

            if (isAvailable(currentTime))
                availableTimes.add(currentTime);
        }

        for (LocalTime currentTime = workingTime.getFrom();
             currentTime.isBefore(LocalTime.of(23, 0));
             currentTime = currentTime.plusHours(1)) {

            if (isAvailable(currentTime))
                availableTimes.add(currentTime);
        }

        return availableTimes;
    }


}
