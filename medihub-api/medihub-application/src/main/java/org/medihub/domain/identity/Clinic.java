package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Clinic {
    public String name;
    public String address;
    public String description;
    Set<AppointmentType> appointmentTypes;
    Set<Appointment> appointments;
    ClinicAdmin clinicAdmin;
    Set<ClinicRoom> clinicRooms;
    Set<MedicalStaff> medicalStaff;

}
