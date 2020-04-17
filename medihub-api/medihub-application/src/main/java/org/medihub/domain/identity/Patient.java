package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Patient  extends  User{
    ClinicalCenter cc;
    MedicalRecord mr;
    User u;
    Set<Appointment> a;
}
