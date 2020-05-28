package org.medihub.application.ports.incoming.medical_doctor.schedule;

public class VacationScheduleItemOutput extends DailyScheduleItemOutput {
    public VacationScheduleItemOutput(Long id, String time, String type) {
        super(id, time, type);
    }
}
