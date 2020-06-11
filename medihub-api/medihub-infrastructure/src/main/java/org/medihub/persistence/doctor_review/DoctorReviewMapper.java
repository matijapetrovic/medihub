package org.medihub.persistence.doctor_review;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.medical_doctor.MedicalDoctorReview;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.medihub.persistence.patient.PatientMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DoctorReviewMapper {
    private final MedicalDoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    public MedicalDoctorReview mapToDomainEntity(DoctorReviewJpaEntity doctorReview) {
        return new MedicalDoctorReview(
                doctorReview.getId(),
                doctorReview.getRating(),
                patientMapper.mapToDomainEntity(doctorReview.getPatient()),
                doctorMapper.mapToDomainEntity(doctorReview.getDoctor()),
                doctorReview.getCanReview());
    }

    public DoctorReviewJpaEntity mapToJpaEntity(MedicalDoctorReview doctorReview) {
        return new DoctorReviewJpaEntity(
                doctorReview.getId(),
                doctorReview.getRating(),
                doctorMapper.mapToJpaEntity(doctorReview.getDoctor()),
                patientMapper.mapToJpaEntity(doctorReview.getPatient()),
                doctorReview.getCanReview());
    }

    public List<MedicalDoctorReview> mapToDomainEntityList(List<DoctorReviewJpaEntity> doctorReviews) {
        return doctorReviews
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
