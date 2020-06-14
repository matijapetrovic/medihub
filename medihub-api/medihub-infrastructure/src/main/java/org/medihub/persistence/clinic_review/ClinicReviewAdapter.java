package org.medihub.persistence.clinic_review;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.reviewing.GetClinicReviewsPort;
import org.medihub.application.ports.outgoing.reviewing.LoadClinicReviewPort;
import org.medihub.application.ports.outgoing.reviewing.SaveClinicReviewPort;
import org.medihub.domain.clinic.ClinicReview;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ClinicReviewAdapter implements
        SaveClinicReviewPort,
        LoadClinicReviewPort,
        GetClinicReviewsPort {
    private final ClinicReviewMapper clinicReviewMapper;
    private final ClinicReviewRepository clinicReviewRepository;

    @Override
    public void saveClinicReview(ClinicReview clinicReview) {
        clinicReviewRepository.save(clinicReviewMapper.mapToJpaEntity(clinicReview));
    }

    @Override
    public ClinicReview loadByPatientIdAndClinicId(Long patientId, Long clinicId) {
        Optional<ClinicReviewJpaEntity> clinicReview =
                clinicReviewRepository.findByPatientIdAndClinicId(patientId, clinicId);

        if (clinicReview.isEmpty())
            return null;

        return clinicReviewMapper.mapToDomainEntity(clinicReview.get());
    }

    @Override
    public ClinicReview loadById(Long clinicReviewId) {
        Optional<ClinicReviewJpaEntity> clinicReview =
                clinicReviewRepository.findById(clinicReviewId);

        if (clinicReview.isEmpty())
            return null;

        return clinicReviewMapper.mapToDomainEntity(clinicReview.get());

    }

    @Override
    public ClinicReview loadByIdWithLock(Long id) {
        Optional<ClinicReviewJpaEntity> clinicReview =
                clinicReviewRepository.findByIdWithLock(id);

        if (clinicReview.isEmpty())
            return null;

        return clinicReviewMapper.mapToDomainEntity(clinicReview.get());
    }

    @Override
    public List<ClinicReview> getClinicsReviewsForReview(Long patientId) {
        return clinicReviewMapper.mapToDomainEntityList(clinicReviewRepository.findAllByPatientIdAndCanReviewTrue(
                patientId));
    }
}
