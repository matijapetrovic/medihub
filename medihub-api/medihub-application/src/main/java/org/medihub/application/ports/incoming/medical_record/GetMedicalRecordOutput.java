package org.medihub.application.ports.incoming.medical_record;

import lombok.Value;
import org.medihub.application.ports.incoming.medical_record.dto.AllergyDTO;
import org.medihub.application.ports.incoming.medical_record.dto.FinishedAppointmentDTO;

import java.util.List;

@Value
public class GetMedicalRecordOutput {
    Long id;
    Integer height;
    Integer weight;
    String bloodType;
    Boolean rhPositive;
    Double leftDioptry;
    Double rightDioptry;
    List<AllergyDTO> allergies;
    List<FinishedAppointmentDTO> finishedAppointments;
}
