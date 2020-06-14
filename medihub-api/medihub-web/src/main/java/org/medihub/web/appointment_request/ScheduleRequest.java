package org.medihub.web.appointment_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleRequest {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private Long doctorId;
    private Long clinicRoomId;
}
