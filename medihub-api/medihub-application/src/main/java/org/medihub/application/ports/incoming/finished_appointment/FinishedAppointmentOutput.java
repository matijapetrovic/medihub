package org.medihub.application.ports.incoming.finished_appointment;

import lombok.Value;
import org.medihub.domain.Diagnosis;
import org.medihub.domain.appointment.Appointment;

@Value
public class FinishedAppointmentOutput {
    Long id;
    String description;
    Long appointment;
    Long diagnosis;
}
