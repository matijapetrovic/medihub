package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DrugCodebook {
    ClinicalCenter clinicalCenter;
    Set<Drug> drugs;
}
