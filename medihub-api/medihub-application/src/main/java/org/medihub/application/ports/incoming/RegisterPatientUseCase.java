package org.medihub.application.ports.incoming;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;
import org.medihub.common.validation.annotations.InsuranceNumber;
import org.medihub.common.validation.annotations.Password;
import org.medihub.common.validation.annotations.TelephoneNumber;
import org.medihub.domain.patient.RegistrationRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface RegisterPatientUseCase {
    RegistrationRequest registerPatient(RegisterPatientCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class RegisterPatientCommand extends SelfValidating<RegisterPatientCommand> {

        @Email
        String email;
        @Password
        String password;
        @InsuranceNumber
        String insuranceNumber;
        @NotBlank
        String firstName;
        @NotBlank
        String lastName;
        @NotBlank
        String address;
        @NotBlank
        String city;
        @NotBlank
        String country;
        @TelephoneNumber
        String telephoneNumber;

        public RegisterPatientCommand(
                String email,
                String password,
                String firstName,
                String lastName,
                String address,
                String city,
                String country,
                String telephoneNumber,
                String insuranceNumber) {
            this.email = email;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.city = city;
            this.country = country;
            this.telephoneNumber = telephoneNumber;
            this.insuranceNumber = insuranceNumber;
            this.validateSelf();
        }
    }
}