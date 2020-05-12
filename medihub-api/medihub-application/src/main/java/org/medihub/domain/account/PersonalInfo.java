package org.medihub.domain.account;

import lombok.Value;
import org.medihub.domain.account.Address;

@Value
public class PersonalInfo {
    String firstName;
    String lastName;
    Address address;
    String telephoneNumber;

    public String getAddress() {
        return address.getAddressLine();
    }

    public String getCity() {
        return address.getCity();
    }

    public String getCountry() {
        return address.getCountry();
    }
}
