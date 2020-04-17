package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MedicalRecord {
    Set<Diagnosis> diagnosis;
    Patient p;
}
