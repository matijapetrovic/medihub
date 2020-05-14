package org.medihub.application.ports.incoming.appointment_type;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class RemoveAppointmentTypeOutput {
    Long id;
    String name;
}
