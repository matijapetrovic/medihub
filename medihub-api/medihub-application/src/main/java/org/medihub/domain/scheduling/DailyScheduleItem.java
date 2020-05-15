package org.medihub.domain.scheduling;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@AllArgsConstructor
public class DailyScheduleItem {
    private Long id;
    @EqualsAndHashCode.Include
    private LocalTime time;
}
