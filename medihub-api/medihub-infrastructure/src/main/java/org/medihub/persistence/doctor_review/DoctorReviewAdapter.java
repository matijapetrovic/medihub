package org.medihub.persistence.doctor_review;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.reviewing.GetDoctorReviewsPort;
import org.medihub.application.ports.outgoing.reviewing.LoadDoctorReviewPort;
import org.medihub.application.ports.outgoing.reviewing.SaveDoctorReviewPort;
import org.medihub.domain.medical_doctor.MedicalDoctorReview;
import org.medihub.persistence.clinic_review.ClinicReviewJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DoctorReviewAdapter implements
        SaveDoctorReviewPort,
        LoadDoctorReviewPort,
        GetDoctorReviewsPort {
    private final DoctorReviewMapper mapper;
    private final DoctorReviewRepository repository;

    @Override
    public void saveDoctorReview(MedicalDoctorReview doctorReview) {
        repository.save(mapper.mapToJpaEntity(doctorReview));
    }

    @Override
    public MedicalDoctorReview loadByPatientIdAndDoctorId(Long patientId, Long doctorId) {
        Optional<DoctorReviewJpaEntity> doctorReview =
                repository.findByPatientIdAndDoctorId(patientId, doctorId);

        if (doctorReview.isEmpty())
            return null;

        return mapper.mapToDomainEntity(doctorReview.get());
    }

    @Override
    public MedicalDoctorReview loadByIdWithLock(Long id) {
        Optional<DoctorReviewJpaEntity> doctorReview =
                repository.findByIdWithLock(id);

        if (doctorReview.isEmpty())
            return null;

        return mapper.mapToDomainEntity(doctorReview.get());
    }

    @Override
    public List<MedicalDoctorReview> getDoctorReviewsForReview(Long patientId) {
        return mapper.mapToDomainEntityList(repository.findAllByPatientIdAndCanReviewTrue(patientId));
    }
}
