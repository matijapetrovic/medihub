package org.medihub.persistence.clinic_review;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.clinic.ClinicReview;
import org.medihub.persistence.clinic.ClinicMapper;
import org.medihub.persistence.patient.PatientMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicReviewMapper {
    private final ClinicMapper clinicMapper;
    private final PatientMapper patientMapper;

    public ClinicReview mapToDomainEntity(ClinicReviewJpaEntity clinicReview) {
        return new ClinicReview(
                clinicReview.getId(),
                clinicReview.getRating(),
                patientMapper.mapToDomainEntity(clinicReview.getPatient()),
                clinicMapper.mapToDomainEntity(clinicReview.getClinic()),
                clinicReview.getCanReview());
    }

    public ClinicReviewJpaEntity mapToJpaEntity(ClinicReview clinicReview) {
        return new ClinicReviewJpaEntity(
                clinicReview.getId(),
                clinicReview.getRating(),
                clinicMapper.mapToJpaEntity(clinicReview.getClinic()),
                patientMapper.mapToJpaEntity(clinicReview.getPatient()),
                clinicReview.getCanReview());
    }

    public List<ClinicReview> mapToDomainEntityList(List<ClinicReviewJpaEntity> clinicReviews) {
        return clinicReviews
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
