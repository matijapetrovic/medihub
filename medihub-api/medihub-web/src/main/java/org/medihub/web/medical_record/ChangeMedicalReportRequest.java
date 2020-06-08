package org.medihub.web.medical_record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.medihub.application.ports.incoming.medical_record.AllergyDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeMedicalReportRequest {
    Long id;
    Integer height;
    Integer weight;
    String bloodType;
    Boolean rhPositive;
    Double leftDioptry;
    Double rightDioptry;
    List<AllergyDTO> allergies;
}
