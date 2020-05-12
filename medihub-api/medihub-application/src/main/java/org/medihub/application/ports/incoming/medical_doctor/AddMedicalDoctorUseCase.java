package org.medihub.application.ports.incoming.medical_doctor;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;
import org.medihub.common.validation.annotations.Password;
import org.medihub.common.validation.annotations.TelephoneNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface AddMedicalDoctorUseCase {
    void addDoctor(AddMedicalDoctorCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddMedicalDoctorCommand extends SelfValidating<AddMedicalDoctorUseCase.AddMedicalDoctorCommand> {
        @Email
        String email;
        @Password
        String password;
        @NotBlank
        String firstName;
        @NotBlank
        String lastName;
        @NotBlank
        String addressLine;
        @NotBlank
        String city;
        @NotBlank
        String country;
        @TelephoneNumber
        String telephoneNumber;
        @NotBlank
        String from; // TODO: validate that it's a time
        @NotBlank
        String to;
        @NotNull
        Long appointmentTypeId;

        public AddMedicalDoctorCommand(
                String email,
                String password,
                String firstName,
                String lastName,
                String addressLine,
                String city,
                String country,
                String telephoneNumber,
                String from,
                String to,
                Long appointmentTypeId){
            this.email = email;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.addressLine = addressLine;
            this.city = city;
            this.country = country;
            this.telephoneNumber = telephoneNumber;
            this.from = from;
            this.to = to;
            this.appointmentTypeId = appointmentTypeId;
            this.validateSelf();
        }
    }
}
