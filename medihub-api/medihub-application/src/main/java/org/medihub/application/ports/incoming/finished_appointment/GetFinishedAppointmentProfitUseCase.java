package org.medihub.application.ports.incoming.finished_appointment;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface GetFinishedAppointmentProfitUseCase {
    FinishedAppointmentProfitResponse getProfit(GetFinishedAppointmentProfitCommand getFinishedAppointmentProfitCommand);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class GetFinishedAppointmentProfitCommand extends SelfValidating<GetFinishedAppointmentProfitUseCase.GetFinishedAppointmentProfitCommand> {
        @NotNull
        LocalDate from;
        @NotNull
        LocalDate to;

        public GetFinishedAppointmentProfitCommand(
                LocalDate from,
                LocalDate to) {
            this.from = from;
            this.to = to;
        }
    }
}
