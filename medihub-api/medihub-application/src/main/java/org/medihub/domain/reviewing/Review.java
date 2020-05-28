package org.medihub.domain.reviewing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.appointment.FinishedAppointment;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public abstract class Review {
    private Long id;
    private BigDecimal rating;
    private FinishedAppointment appointment;
}
