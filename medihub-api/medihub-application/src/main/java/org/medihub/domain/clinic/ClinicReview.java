package org.medihub.domain.clinic;

import lombok.Getter;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.reviewing.Review;

import java.math.BigDecimal;

@Getter
public class ClinicReview extends Review {
    private Clinic clinic;
    public ClinicReview(Long id, BigDecimal rating, FinishedAppointment appointment, Clinic clinic) {
        super(id, rating, appointment);
        this.clinic = clinic;
    }
}
