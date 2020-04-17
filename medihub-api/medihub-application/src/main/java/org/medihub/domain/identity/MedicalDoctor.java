package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MedicalDoctor extends  MedicalStaff {
    Set<Appointment> a;
}
