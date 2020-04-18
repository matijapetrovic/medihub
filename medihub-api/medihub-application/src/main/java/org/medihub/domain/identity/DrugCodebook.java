package org.medihub.domain.identity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class DrugCodebook {
    private Set<Drug> drugs;
    private ClinicalCenter clinicalCenter;
}
