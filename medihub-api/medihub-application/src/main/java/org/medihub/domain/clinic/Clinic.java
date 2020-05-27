package org.medihub.domain.clinic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.medihub.domain.MedicalStaff;
import org.medihub.domain.Money;
import org.medihub.domain.account.Address;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.AppointmentType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class Clinic {
    private Long id;
    private String name;
    private Address address;
    private String description;
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
            String description,
            Map<AppointmentType, Money> appointmentPrices) {
        this(name, address, description);
        this.id = id;
        this.appointmentPrices = appointmentPrices;
    }

    public Money getPrice(AppointmentType appointmentType) {
        return appointmentPrices.get(appointmentType);
    }

    public void addPrice(AppointmentType appointmentType, Money money) { this.appointmentPrices.put(appointmentType, money);}
}
