package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Patient  extends  User{
    ClinicalCenter clinicalCenter;
    MedicalRecord medicalRecord;
    Set<Appointment> apointments;
}
