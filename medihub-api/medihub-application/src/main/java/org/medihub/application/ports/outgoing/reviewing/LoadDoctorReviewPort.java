package org.medihub.application.ports.outgoing.reviewing;

import org.medihub.domain.medical_doctor.MedicalDoctorReview;

public interface LoadDoctorReviewPort {
    MedicalDoctorReview loadByAppointmentId(Long appointmentId);
}
