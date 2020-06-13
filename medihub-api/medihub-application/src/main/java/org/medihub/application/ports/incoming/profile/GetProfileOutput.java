package org.medihub.application.ports.incoming.profile;

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
