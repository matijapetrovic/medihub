package org.medihub.domain.medical_doctor;

import lombok.Getter;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.reviewing.Review;

import java.math.BigDecimal;

@Getter
public class MedicalDoctorReview extends Review {
    private MedicalDoctor doctor;
    public MedicalDoctorReview(Long id, BigDecimal rating, FinishedAppointment appointment, MedicalDoctor doctor) {
        super(id, rating, appointment);
        this.doctor = doctor;
    }
}
