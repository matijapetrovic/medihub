package org.medihub.domain.identity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class DiagnosisCodebook {
    private Set<Diagnosis> diagnosis;
    private ClinicalCenter clinicalCenter;
}
