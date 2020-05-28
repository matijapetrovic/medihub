package org.medihub.persistence.doctor_review;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.reviewing.SaveDoctorReviewPort;
import org.medihub.domain.medical_doctor.MedicalDoctorReview;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorReviewAdapter implements SaveDoctorReviewPort {
    private final DoctorReviewMapper mapper;
    private final DoctorReviewRepository repository;

    @Override
    public void saveDoctorReview(MedicalDoctorReview doctorReview) {
        repository.save(mapper.mapToJpaEntity(doctorReview));
    }
}
