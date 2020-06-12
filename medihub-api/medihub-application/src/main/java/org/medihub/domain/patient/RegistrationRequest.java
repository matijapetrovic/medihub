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
        Account account = new Account(
                null,
                email,
                password,
                true,
                List.of(new Authority(1L, "ROLE_PATIENT")));

        PersonalInfo personalInfo = new PersonalInfo(
                null,
                firstName,
                lastName,
                address,
                telephoneNumber,
                account);

        return new Patient(
               null,
               insuranceNumber,
               personalInfo);
    }
}
