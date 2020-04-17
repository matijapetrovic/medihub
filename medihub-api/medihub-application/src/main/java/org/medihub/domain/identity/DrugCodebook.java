package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DrugCodebook {
    ClinicalCenter cc;
    Set<Drug> d;
}
