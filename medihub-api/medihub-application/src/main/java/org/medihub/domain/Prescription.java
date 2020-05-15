package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.medical_nurse.MedicalNurse;

@AllArgsConstructor
@Getter
public class Prescription {
    private Long id;
    private Drug drug;
    private MedicalNurse medicalNurse;
}
