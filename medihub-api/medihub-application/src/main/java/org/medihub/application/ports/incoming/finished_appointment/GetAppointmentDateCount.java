package org.medihub.application.ports.incoming.finished_appointment;

import lombok.Value;

@Value
public class GetAppointmentDateCount {
    private String date;
    private String time;
    private Integer count;
}
