package org.medihub.domain.clinic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.Money;
import org.medihub.domain.account.Address;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.reviewing.Rating;

import java.util.Map;

@AllArgsConstructor
@Getter
public class Clinic {
    private Long id;
    private String name;
    private Address address;
    private String description;
    private Map<AppointmentType, Money> appointmentPrices;
    private Rating rating;

    public Money getPrice(AppointmentType appointmentType) {
        return appointmentPrices.get(appointmentType);
    }
}
