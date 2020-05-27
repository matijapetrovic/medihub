package org.medihub.application.ports.incoming.medical_doctor.schedule;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DailyScheduleItemOutput {
    public Long id;
    public String time;
    public String type;
}