package org.medihub.web.predefined_appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PredefinedAppointmentRequest {
    private Long doctorId;
    private String start;
    private double duration;
    private Long clinicRoomId;
    private Long appointmentTypeId;
}
