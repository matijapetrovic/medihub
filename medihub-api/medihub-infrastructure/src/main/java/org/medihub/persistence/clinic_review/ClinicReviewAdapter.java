package org.medihub.persistence.clinic_review;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.reviewing.LoadClinicReviewPort;
import org.medihub.application.ports.outgoing.reviewing.SaveClinicReviewPort;
import org.medihub.domain.clinic.ClinicReview;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ClinicReviewAdapter implements
        SaveClinicReviewPort,
        LoadClinicReviewPort {
    private final ClinicReviewMapper clinicReviewMapper;
    private final ClinicReviewRepository clinicReviewRepository;

    @Override
    public void saveClinicReview(ClinicReview clinicReview) {
        clinicReviewRepository.save(clinicReviewMapper.mapToJpaEntity(clinicReview));
    }

    @Override
    public ClinicReview loadByAppointmentId(Long appointmentId) {
        Optional<ClinicReviewJpaEntity> clinicReview =
                clinicReviewRepository.findByAppointment_Id(appointmentId);

        if (clinicReview.isEmpty())
            return null;

        return clinicReviewMapper.mapToDomainEntity(clinicReview.get());
    }
}
