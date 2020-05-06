package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
public class Clinic {
    private Long id;
    private String name;
    private Address address;
    private String description;
    private ClinicalCenter clinicalCenter;
    private Set<AppointmentType> appointmentTypes;
    private Set<Appointment> appointments;
    private ClinicAdmin clinicAdmin;
    private Set<ClinicRoom> clinicRooms;
    private Set<MedicalStaff> medicalStaff;

    public Clinic() {
        this.appointmentTypes = new HashSet<>();
        this.appointments = new HashSet<>();
        this.clinicRooms = new HashSet<>();
        this.medicalStaff = new HashSet<>();
    }

    public Clinic(
            String name,
            Address address,
            String description) {
        this();
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public Clinic(
            Long id,
            String name,
            Address address,
            String description) {
        this(name, address, description);
        this.id = id;
    }
}
