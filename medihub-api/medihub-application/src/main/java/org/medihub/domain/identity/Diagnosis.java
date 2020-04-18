package org.medihub.domain.identity;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Diagnosis {
    DiagnosisCodebook diagnosisCodebook;
    Set<MedicalRecord> medicalRecords;
}
