package org.medihub.application.ports.incoming.medical_record;

import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
public class GetMedicalRecordOutput {
    Long id;
    Integer height;
    Integer weight;
    String bloodType;
    Boolean rhPositive;
    Double leftDioptry;
    Double rightDioptry;
    Map<String, String> allergies;
    List<String> diagnoses;
}
