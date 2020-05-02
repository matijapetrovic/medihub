package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Prescription {
    private Long id;
    private Drug drug;
    private MedicalNurse medicalNurse;
}
