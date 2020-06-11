package org.medihub.domain.reviewing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.patient.Patient;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public abstract class Review {
    private Long id;
    private BigDecimal rating;
    private Patient patient;
    private Boolean canReview;

    public void updateRating(BigDecimal rating) {
        this.rating = rating;
        this.canReview = false;
    }
    public void enableReview() { this.canReview = true; }
}
