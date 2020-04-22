package org.medihub.web.security.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProfileRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String telephoneNum;
}
