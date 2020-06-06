package org.medihub.application.ports.incoming.appointment;

import lombok.Value;

@Value
public class GetAppointmentsOutput {
    Long id;
    String name;
    String doctorFullName;
    String clinicName;
    String date;
    String time;
}
