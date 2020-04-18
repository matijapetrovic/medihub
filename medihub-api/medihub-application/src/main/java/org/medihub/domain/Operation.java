package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class Operation extends  Appointment {
    private Set<MedicalDoctor> medicalDoctors;
}
