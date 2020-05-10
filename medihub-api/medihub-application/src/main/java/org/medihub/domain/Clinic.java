package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.AppointmentType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Getter
public class Clinic {
    private Long id;
    private String name;
    private Address address;
    private String description;
    private ClinicalCenter clinicalCenter;
    private Map<AppointmentType, Money> appointmentPrices;
    private Set<Appointment> appointments;
    private ClinicAdmin clinicAdmin;
    private Set<MedicalStaff> medicalStaff;

    public Clinic() {
        appointmentPrices = new HashMap<>();
        appointments = new HashSet<>();
        medicalStaff = new HashSet<>();
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

    public Money getPrice(AppointmentType appointmentType) {
        return appointmentPrices.get(appointmentType);
    }
}
