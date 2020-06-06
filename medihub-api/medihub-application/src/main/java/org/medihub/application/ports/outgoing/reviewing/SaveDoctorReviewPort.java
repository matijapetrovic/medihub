package org.medihub.application.ports.outgoing.reviewing;

import org.medihub.domain.medical_doctor.MedicalDoctorReview;

public interface SaveDoctorReviewPort {
    void saveDoctorReview(MedicalDoctorReview doctorReview);
}

