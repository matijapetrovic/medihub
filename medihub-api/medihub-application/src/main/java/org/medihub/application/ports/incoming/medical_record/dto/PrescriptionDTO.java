package org.medihub.application.ports.incoming.medical_record.dto;

import lombok.Value;

@Value
public class PrescriptionDTO {
    String drug;
    Boolean validated;
}
