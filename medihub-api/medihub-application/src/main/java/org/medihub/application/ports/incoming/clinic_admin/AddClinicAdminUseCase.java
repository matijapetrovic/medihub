package org.medihub.application.ports.incoming.clinic_admin;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.common.SelfValidating;
import org.medihub.common.validation.annotations.Password;
import org.medihub.common.validation.annotations.TelephoneNumber;
import org.medihub.domain.ClinicAdmin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface AddClinicAdminUseCase {
    ClinicAdmin addClinicAdmin(AddClinicAdminCommand command) throws AccountNotFoundException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddClinicAdminCommand extends SelfValidating<AddClinicAdminCommand> {

        @Email
        String email;
        @Password
        String password;
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

        public AddClinicAdminCommand(
                String email,
                String password,
                String firstName,
                String lastName,
                String address,
                String city,
                String country,
                String telephoneNumber) {
            this.email = email;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.city = city;
            this.country = country;
            this.telephoneNumber = telephoneNumber;
            this.validateSelf();
        }
    }
}
