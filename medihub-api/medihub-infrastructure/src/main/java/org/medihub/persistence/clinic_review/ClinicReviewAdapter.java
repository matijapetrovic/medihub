package org.medihub.persistence.clinic_review;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.reviewing.SaveClinicReviewPort;
import org.medihub.domain.clinic.ClinicReview;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClinicReviewAdapter implements SaveClinicReviewPort {
    private final ClinicReviewMapper clinicReviewMapper;
    private final ClinicReviewRepository clinicReviewRepository;

    @Override
    public void saveClinicReview(ClinicReview clinicReview) {
        clinicReviewRepository.save(clinicReviewMapper.mapToJpaEntity(clinicReview));
    }
}
