package org.medihub.web.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String telephoneNum;
    private String securityNum;
}
