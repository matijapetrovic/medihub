package org.medihub.web.medical_record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
