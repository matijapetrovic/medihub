package org.medihub.domain.identity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClinicalCenter {
    public string name;
    DiagnosisCodebook diagnosisCodebook;
    Set<Patient> patients;
    Set<ClinicCenterAdmin> ccAdmins;
    Set<Clinic> clinics;
    DrugCodebook drugCodebook;
}
