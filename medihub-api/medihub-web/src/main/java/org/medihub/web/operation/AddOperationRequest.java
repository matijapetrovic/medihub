package org.medihub.web.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddOperationRequest {
    private Long requestId;
    private LocalDate date;
    private LocalTime time;
    private Long doctorId;
    private Long clinicRoomId;
    List<Long> presentDoctors;
}
