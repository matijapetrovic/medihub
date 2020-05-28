package org.medihub.persistence.doctor_review;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.reviewing.LoadDoctorReviewPort;
import org.medihub.application.ports.outgoing.reviewing.SaveDoctorReviewPort;
import org.medihub.domain.medical_doctor.MedicalDoctorReview;
import org.medihub.persistence.clinic_review.ClinicReviewJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DoctorReviewAdapter implements
        SaveDoctorReviewPort,
        LoadDoctorReviewPort {
    private final DoctorReviewMapper mapper;
    private final DoctorReviewRepository repository;

    @Override
    public void saveDoctorReview(MedicalDoctorReview doctorReview) {
        repository.save(mapper.mapToJpaEntity(doctorReview));
    }

    @Override
    public MedicalDoctorReview loadByAppointmentId(Long appointmentId) {
        Optional<DoctorReviewJpaEntity> doctorReview =
                repository.findByAppointment_Id(appointmentId);

        if (doctorReview.isEmpty())
            return null;

        return mapper.mapToDomainEntity(doctorReview.get());
    }
}
