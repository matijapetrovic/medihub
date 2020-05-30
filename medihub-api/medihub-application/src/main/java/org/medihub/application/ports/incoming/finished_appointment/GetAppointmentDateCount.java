package org.medihub.application.ports.incoming.finished_appointment;

import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

@Value
public class GetAppointmentDateCount{
    private String date;
    private Integer count;

    public LocalTime getDailyValue() {
        return LocalTime.parse(this.getDate());
    }

    public LocalDate getMonthlyValue() {
        return LocalDate.parse(this.getDate());
    }

    public Month getAnnualValue() {
        return Month.valueOf(this.date);
    }
}
