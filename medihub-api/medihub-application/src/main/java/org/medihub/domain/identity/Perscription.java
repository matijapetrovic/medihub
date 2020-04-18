package org.medihub.domain.identity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Perscription {
    private Drug drug;
    private MedicalNurse medicalNurse;
}
