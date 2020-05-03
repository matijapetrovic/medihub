package org.medihub.application.ports.incoming.clinic;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class SearchClinicsOutput {
    String name;
    Double rating;
    String address;
    String city;
    String country;
    BigDecimal appointmentPrice;
}
