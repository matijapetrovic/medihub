package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Drug {
    DrugCodebook drugCodebook;
    Set<Perscription> p;
}
