package org.medihub.domain.clinic;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
public class Clinic {
    private Long id;
    private String name;
    private Address address;
    private String description;
    private Map<AppointmentType, Money> appointmentPrices;
    private ClinicRating rating;

    public Money getPrice(AppointmentType appointmentType) {
        return appointmentPrices.get(appointmentType);
    }
}
