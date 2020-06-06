package org.medihub.application.ports.incoming.finished_appointment;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class FinishedAppointmentProfitResponse {
    private BigDecimal profit;
    private BigDecimal longTermProfit;
    private String longTermProfitDescription;
}
