package org.medihub.application.ports.incoming.clinic;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

public interface UpdateClinicUseCase {
    void updateClinic(UpdateClinicCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class UpdateClinicCommand extends SelfValidating<UpdateClinicUseCase.UpdateClinicCommand> {
        @NotBlank
        String name;
        @NotBlank
        String addressLine;
        @NotBlank
        String city;
        @NotBlank
        String country;
        @NotBlank
        String description;
        @NotNull
        Map<String, BigDecimal> appointmentPrices;

        public UpdateClinicCommand(
                String name,
                String addressLine,
                String city,
                String country,
                String description,
                Map<String, BigDecimal> appointmentPrices) {
            this.name = name;
            this.addressLine = addressLine;
            this.city = city;
            this.country = country;
            this.description = description;
            this.appointmentPrices = appointmentPrices;
        }
    }
}
