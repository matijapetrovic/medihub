package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class DrugCodebook {
    private Long id;
    private Set<Drug> drugs;
    private ClinicalCenter clinicalCenter;
}
