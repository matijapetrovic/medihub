package org.medihub.domain.clinic;

import lombok.Getter;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.patient.Patient;
import org.medihub.domain.reviewing.Review;

import java.math.BigDecimal;

@Getter
public class ClinicReview extends Review {
    private Clinic clinic;
    public ClinicReview(Long id, BigDecimal rating, Patient patient, Clinic clinic, Boolean canReview) {
        super(id, rating, patient, canReview);
        this.clinic = clinic;
    }
}
