package org.medihub.domain.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.account.Account;
import org.medihub.domain.account.Address;
import org.medihub.domain.account.Authority;
import org.medihub.domain.account.PersonalInfo;

import java.util.List;

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

    public Patient accept() {
        PersonalInfo personalInfo = new PersonalInfo(
                firstName,
                lastName,
                address,
                telephoneNumber);

        Account account = new Account(
                null,
                email,
                password,
                personalInfo,
                true,
                List.of(new Authority(1L, "ROLE_PATIENT")));

        return new Patient(
               null,
               insuranceNumber,
               account);
    }
}
