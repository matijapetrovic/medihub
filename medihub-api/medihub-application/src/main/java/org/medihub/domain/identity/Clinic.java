package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Clinic {
    Set<AppointmentType> at;
    public String name;
    publiv String address;
    public String description;
    Set<Appointment> a;
    Set<Appointment> a2;
    Set<ClinicRoom> cr;
    Set<ClinicAdmin> ca;
    Set<MedicalStaff> ms;

}
