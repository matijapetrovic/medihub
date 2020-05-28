package org.medihub.application.ports.incoming.medical_doctor.schedule;

public class VacationScheduleItemOutput extends DailyScheduleItemOutput {
    public String endDate;

    public VacationScheduleItemOutput(Long id, String time, String type, String endDate) {
        super(id, time, type);
        this.endDate = endDate;
    }
}
