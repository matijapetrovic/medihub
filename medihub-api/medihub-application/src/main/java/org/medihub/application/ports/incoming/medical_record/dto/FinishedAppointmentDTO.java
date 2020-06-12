package org.medihub.application.ports.incoming.medical_record.dto;

import lombok.Value;

import java.util.List;

@Value
public class FinishedAppointmentDTO {
    String date;
    String doctor;
    String diagnosis;
    String description;
    List<PrescriptionDTO> prescriptions;
}
