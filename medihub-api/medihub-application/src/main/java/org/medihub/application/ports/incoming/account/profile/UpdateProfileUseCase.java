package org.medihub.application.ports.incoming.profile;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.common.SelfValidating;
import org.medihub.common.validation.annotations.TelephoneNumber;

import javax.validation.constraints.NotNull;

public interface UpdateProfileUseCase {
    void updateProfile(UpdateProfileCommand command) throws AccountNotFoundException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class UpdateProfileCommand extends SelfValidating<UpdateProfileCommand> {
        @NotNull
        String email;
        String firstName;
        String lastName;
        String address;
        String city;
        String country;
        @TelephoneNumber
        String telephoneNumber;

        public UpdateProfileCommand(
                String email,
                String firstName,
                String lastName,
                String address,
                String city,
                String country,
                String telephoneNumber) {
            this.email = email;
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
