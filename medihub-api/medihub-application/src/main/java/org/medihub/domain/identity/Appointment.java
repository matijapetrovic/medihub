package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class Appointment {
    Set<Patient> p;
    Date date;
    Clinic c;
    ClinicRoom cr;
    Set<MedicalDoctor> md;
    Set<Perscription> p;
}
