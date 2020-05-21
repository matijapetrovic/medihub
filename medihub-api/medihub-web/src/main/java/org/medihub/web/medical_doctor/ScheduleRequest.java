package org.medihub.web.medical_doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {
    String date;
    String time;
    Long patientId;
    Long doctorId;
    Long clinicRoomId;
}
