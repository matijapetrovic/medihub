package org.medihub.application.ports.incoming.finished_appointment;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class GetAppointmentHistoryOutput {
    String name;
    String doctorFullName;
    String date;
    String time;
    BigDecimal clinicRating;
}
