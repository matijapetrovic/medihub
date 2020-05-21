package org.medihub.application.ports.incoming.medical_doctor;

import lombok.Value;

import java.util.Map;

@Value
public class GetDoctorScheduleOutput {
    Map<String, DailyScheduleOutput> dailySchedules;
}
