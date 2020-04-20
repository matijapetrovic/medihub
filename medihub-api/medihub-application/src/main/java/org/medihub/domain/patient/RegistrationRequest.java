package org.medihub.domain.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor()
public class RegistrationRequest {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Address address;
    private String telephoneNumber;
    private String insuranceNumber;

    public void accept() {

    }

    public void reject() {

    }
}
