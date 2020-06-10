package org.medihub.web.finished_appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangeFinishedAppointmentRequest {
    Long id;
    String description;
    Long diagnosis;
}
