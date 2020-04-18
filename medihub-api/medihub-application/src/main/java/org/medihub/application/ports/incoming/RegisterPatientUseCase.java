package org.medihub.application.ports.incoming;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;
import org.medihub.domain.patient.RegistrationRequest;

public interface RegisterPatientUseCase {
    RegistrationRequest registerPatient(RegisterPatientCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class RegisterPatientCommand extends SelfValidating<RegisterPatientCommand> {
        String email;
        String password;
        String firstName;
        String lastName;
        String address;
        String city;
        String country;
        String telephoneNum;
        String securityNum;

        public RegisterPatientCommand(
                String email,
                String password,
                String firstName,
                String lastName,
                String address,
                String city,
                String country,
                String telephoneNum,
                String securityNum) {
            this.email = email;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.city = city;
            this.country = country;
            this.telephoneNum = telephoneNum;
            this.securityNum = securityNum;
            this.validateSelf();
        }
    }
}