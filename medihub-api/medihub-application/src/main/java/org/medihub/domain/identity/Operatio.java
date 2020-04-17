package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Operatio extends  Appointment {
    Set<MedicalDoctor> md;
}
