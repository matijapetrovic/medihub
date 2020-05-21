package org.medihub.web.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddAppointmentRequest {
    String date;
    String time;
    Long patientId;
    Long doctorId;
    Long clinicRoomId;
}
