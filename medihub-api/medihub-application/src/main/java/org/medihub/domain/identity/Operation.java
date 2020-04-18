package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Operation extends  Appointment {
    Set<MedicalDoctor> medicalDoctors;
}
