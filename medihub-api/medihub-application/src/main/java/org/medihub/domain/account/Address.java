package org.medihub.domain.account;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class Address {
    String addressLine;
    String city;
    String country;
}
