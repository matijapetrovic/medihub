package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class ClinicalCenter {
    private Long id;
    private String name;
    private DiagnosisCodebook diagnosisCodebook;
    private Set<Patient> patients;
    private Set<ClinicCenterAdmin> ccAdmins;
    private Set<Clinic> clinics;
    private DrugCodebook drugCodebook;
}
