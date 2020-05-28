package org.medihub.application.ports.incoming.clinic;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface AddPriceToAppointmentTypeUseCase {
    void addPrice(AddPriceCommand addPriceCommand);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddPriceCommand extends SelfValidating<AddPriceToAppointmentTypeUseCase.AddPriceCommand> {
        @NotNull
        Long appointmentTypeId;
        @NotNull
        BigDecimal price;

        public AddPriceCommand(
                Long appointmentTypeId,
                BigDecimal price) {
            this.appointmentTypeId = appointmentTypeId;
            this.price = price;
        }
    }
}
