package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Prescription {
    private Drug drug;
    private MedicalNurse medicalNurse;
}
