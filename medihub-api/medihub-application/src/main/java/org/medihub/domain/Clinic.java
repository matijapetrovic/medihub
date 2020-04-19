package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class Clinic {
    private String name;
    private String address;
    private String description;
    private ClinicalCenter clinicalCenter;
    private Set<AppointmentType> appointmentTypes;
    private Set<Appointment> appointments;
    private ClinicAdmin clinicAdmin;
    private Set<ClinicRoom> clinicRooms;
    private Set<MedicalStaff> medicalStaff;

}
