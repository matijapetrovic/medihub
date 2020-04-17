package org.medihub.domain.identity;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Diagnosis {
    DiagnosisCodebook dc;
    Set<MedicalRecord> mr;
}
