package org.medihub.domain.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.medihub.domain.account.Address;

@AllArgsConstructor
@Getter
public class PersonalInfo {
    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private String telephoneNumber;

    private Account account;

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
