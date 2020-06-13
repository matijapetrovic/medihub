package org.medihub.application.ports.outgoing.reviewing;

import org.medihub.domain.medical_doctor.MedicalDoctorReview;

import java.util.List;

public interface GetDoctorReviewsPort {
    List<MedicalDoctorReview> getDoctorReviewsForReview(Long patientId);
}
