package org.medihub.application.ports.incoming.appointment_request;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@AllArgsConstructor
@Value
public class AppointmentRequestResponse {
    private Long id;
    private String doctorEmail;
    private String patientEmail;
    private BigDecimal price;
    private String date;
    private String time;
}
