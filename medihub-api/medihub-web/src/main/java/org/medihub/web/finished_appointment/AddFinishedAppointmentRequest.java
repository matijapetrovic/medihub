package org.medihub.web.finished_appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddFinishedAppointmentRequest {
    Long itemId;
    String description;
    Long appointment;
    Long[] drugs;
    Long diagnosis;
}
