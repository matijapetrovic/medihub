package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class MedicalRecord {
    private Set<Diagnosis> diagnosis;
}