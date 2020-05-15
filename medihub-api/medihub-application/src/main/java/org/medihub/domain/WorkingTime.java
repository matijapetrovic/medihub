package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.HOURS;

@AllArgsConstructor
@Getter
public class WorkingTime {
    private LocalTime from;
    private LocalTime to;

    public long getWorkingHours() {
        return HOURS.between(from, to);
    }
}
