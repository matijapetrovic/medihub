package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

import static java.time.LocalTime.MIDNIGHT;
import static java.time.temporal.ChronoUnit.HOURS;

@AllArgsConstructor
@Getter
public class WorkingTime {
    private LocalTime from;
    private LocalTime to;

    public long getWorkingHours() {
        if (from.isAfter(to))
            return HOURS.between(from, LocalTime.MAX) + HOURS.between(MIDNIGHT, to) + 1;
        return HOURS.between(from, to);
    }
}
