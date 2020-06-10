package org.medihub.domain.medical_doctor;

import lombok.Getter;
import org.medihub.domain.appointment.FinishedAppointment;
import org.medihub.domain.patient.Patient;
import org.medihub.domain.reviewing.Review;

import java.math.BigDecimal;

@Getter
public class MedicalDoctorReview extends Review {
    private MedicalDoctor doctor;
    public MedicalDoctorReview(Long id, BigDecimal rating, Patient patient, MedicalDoctor doctor, Boolean canReview) {
        super(id, rating, patient, canReview);
        this.doctor = doctor;
    }
}
