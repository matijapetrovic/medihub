package org.medihub.application.ports.incoming.clinic;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;
import org.medihub.domain.Clinic;

import javax.validation.constraints.NotNull;

public interface AddClinicUseCase {
    Clinic addClinic(AddClinicCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddClinicCommand extends SelfValidating<AddClinicCommand> {
        @NotNull
        String name;
        @NotNull
        String address;
        @NotNull
        String city;
        @NotNull
        String country;
        @NotNull
        String description;

        public AddClinicCommand(
                String name,
                String address,
                String city,
                String country,
                String description) {
            this.name = name;
            this.address = address;
            this.city = city;
            this.country = country;
            this.description = description;
            this.validateSelf();
        }
    }
}
