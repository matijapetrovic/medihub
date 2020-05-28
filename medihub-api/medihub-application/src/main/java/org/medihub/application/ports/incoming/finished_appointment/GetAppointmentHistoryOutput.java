package org.medihub.application.ports.incoming.finished_appointment;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class GetAppointmentHistoryOutput {
    Long id;
    String name;
    String doctorFullName;
    String clinicName;
    String date;
    String time;
    BigDecimal clinicRating;
}
