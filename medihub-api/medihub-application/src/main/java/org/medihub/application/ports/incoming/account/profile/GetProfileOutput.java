package org.medihub.application.ports.incoming.account.profile;

import lombok.Value;

@Value
public class GetProfileOutput {
    String firstName;
    String lastName;
    String address;
    String city;
    String country;
    String telephoneNum;
}
