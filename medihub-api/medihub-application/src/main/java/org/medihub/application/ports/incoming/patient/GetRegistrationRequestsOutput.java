package org.medihub.application.ports.incoming.patient;

import lombok.Value;

@Value
public class GetRegistrationRequestsOutput {
    Long id;
    String firstName;
    String lastName;
    String email;
    String telephoneNumber;
    String insuranceNumber;
}
