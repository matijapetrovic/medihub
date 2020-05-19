package org.medihub.application.ports.incoming.medical_record;

import lombok.Value;

@Value
public class FinishedAppointmentDTO {
    String date;
    String doctor;
    String diagnosis;
    String description;
}
