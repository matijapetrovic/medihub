package org.medihub.application.ports.incoming.medical_doctor.schedule;

import lombok.Value;
import org.medihub.application.ports.incoming.medical_doctor.schedule.DailyScheduleOutput;

import java.util.Map;

@Value
public class GetScheduleOutput {
    Map<String, DailyScheduleOutput> dailySchedules;
}
