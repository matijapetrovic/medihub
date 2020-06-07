package org.medihub.application.ports.incoming.predefined_appointment;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class GetPredefinedAppointmentsOutput {
    Long id;
    String name;
    String doctor;
    String room;
    String date;
    String time;
    BigDecimal price;
}
