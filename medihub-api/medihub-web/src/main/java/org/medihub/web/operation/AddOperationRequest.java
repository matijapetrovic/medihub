package org.medihub.web.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddOperationRequest {
    String date;
    String time;
    Long patientId;
    Long doctorId;
    Long clinicRoomId;
    List<Long> presentDoctors;
}
