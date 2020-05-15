package org.medihub.domain.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.Diagnosis;

import java.util.Set;

@AllArgsConstructor
@Getter
public class MedicalRecord {
    private Long id;
    private Set<Diagnosis> diagnosis;
}
