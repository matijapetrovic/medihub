package org.medihub.domain;

import lombok.Value;

@Value
public class Address {
    String addressLine;
    String city;
    String country;
}
