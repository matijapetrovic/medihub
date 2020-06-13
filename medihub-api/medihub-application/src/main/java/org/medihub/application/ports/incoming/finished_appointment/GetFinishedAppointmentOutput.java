package org.medihub.application.ports.incoming.finished_appointment;

import lombok.Value;
import org.medihub.domain.Diagnosis;
import org.medihub.domain.appointment.Appointment;

@Value
public class GetFinishedAppointmentOutput {
    Long id;
    String description;
    Long appointment;
    Long diagnosis;
    String date;
    String time;
    String diagnosisStr;
    String type;
}
