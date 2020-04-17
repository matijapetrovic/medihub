package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Drug {
    DrugCodebook dc;
    Set<Perscription> p;
}
