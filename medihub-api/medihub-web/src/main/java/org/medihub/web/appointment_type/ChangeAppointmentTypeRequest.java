package org.medihub.web.appointment_type;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeAppointmentTypeRequest {
    Long id;
    String name;
}
