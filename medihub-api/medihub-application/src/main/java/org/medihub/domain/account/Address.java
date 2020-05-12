package org.medihub.domain.account;

import lombok.Value;

@Value
public class Address {
    String addressLine;
    String city;
    String country;
}
