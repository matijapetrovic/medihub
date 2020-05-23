package org.medihub.application.ports.incoming.medical_doctor.schedule;

import org.medihub.application.ports.incoming.medical_doctor.schedule.DailyScheduleItemOutput;

import java.util.HashSet;
import java.util.Set;

public class DailyScheduleOutput {
    public Long id;
    public Set<DailyScheduleItemOutput> scheduleItems;

    public DailyScheduleOutput() {
        this.scheduleItems = new HashSet<DailyScheduleItemOutput>();
    }
}
