package org.medihub.web.appointment_request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAppointmentRequest {
    private Long doctorId;
    private LocalDate date;
    private LocalTime time;
}
