package org.medihub.application.ports.incoming.clinic;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class SearchClinicsOutput {
    Long id;
    String name;
    BigDecimal rating;
    Integer ratingCount;
    String address;
    String city;
    String country;
    BigDecimal appointmentPrice;
}
