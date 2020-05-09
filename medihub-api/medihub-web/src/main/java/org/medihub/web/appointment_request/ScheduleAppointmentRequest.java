package org.medihub.web.appointment_request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAppointmentRequest {
    private Long patientId;
    private Long doctorId;
    private Long appointmentTypeId;
    private Date date;
}
