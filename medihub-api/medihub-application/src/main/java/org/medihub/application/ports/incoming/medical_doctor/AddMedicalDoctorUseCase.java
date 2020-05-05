package org.medihub.application.ports.incoming.medical_doctor;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

public interface AddMedicalDoctorUseCase {
    void addDoctor(AddMedicalDoctorCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddMedicalDoctorCommand extends SelfValidating<AddMedicalDoctorUseCase.AddMedicalDoctorCommand> {

        Long id;
        String email;
        String password;
        String firstName;
        String lastName;
        String addressLine;
        String city;
        String country;
        String telephoneNumber;
        boolean passwordChanged;

        public AddMedicalDoctorCommand(
                Long id,
                String email,
                String password,
                String firstName,
                String lastName,
                String addressLine,
                String city,
                String country,
                String telephoneNumber,
                boolean passwordChanged){
            this.id = id;
            this.email = email;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.addressLine = addressLine;
            this.city = city;
            this.country = country;
            this.telephoneNumber = telephoneNumber;
            this.passwordChanged = passwordChanged;
        }
    }
}
