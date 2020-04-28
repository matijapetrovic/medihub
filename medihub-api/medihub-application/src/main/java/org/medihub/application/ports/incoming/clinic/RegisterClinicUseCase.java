package org.medihub.application.ports.incoming.clinic;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;

public interface RegisterClinicUseCase {
    void registerClinic(RegisterClinicCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class RegisterClinicCommand extends SelfValidating<RegisterClinicCommand> {
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

        public RegisterClinicCommand(
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
