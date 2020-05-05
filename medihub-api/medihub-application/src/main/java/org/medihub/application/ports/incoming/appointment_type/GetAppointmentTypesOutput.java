package org.medihub.application.ports.incoming.appointment_type;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class GetAppointmentTypesOutput {
    Long id;
    String name;
}
