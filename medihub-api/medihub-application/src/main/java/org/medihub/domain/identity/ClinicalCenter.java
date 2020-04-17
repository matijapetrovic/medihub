package org.medihub.domain.identity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClinicalCenter {
    public string name;
    Set<DiagnosisCodebook> diagnosisCodebook;
    Set<Patient> patient;
    Set<ClinicCenterAdminisration> cca;
    DrugCodebook dc;
}
