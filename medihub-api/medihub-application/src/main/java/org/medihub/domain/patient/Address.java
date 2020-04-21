package org.medihub.domain.patient;

import lombok.Value;

@Value
public class Address {
    String addressLine;
    String city;
    String country;
}
