package org.medihub.persistence.doctor_review;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.clinic.ClinicReview;
import org.medihub.domain.medical_doctor.MedicalDoctorReview;
import org.medihub.persistence.clinic_review.ClinicReviewJpaEntity;
import org.medihub.persistence.finished_appointment.FinishedAppointmentMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorReviewMapper {
    private final FinishedAppointmentMapper appointmentMapper;
    private final MedicalDoctorMapper doctorMapper;

    public MedicalDoctorReview mapToDomainEntity(DoctorReviewJpaEntity doctorReview) {
        return new MedicalDoctorReview(
                doctorReview.getId(),
                doctorReview.getRating(),
                appointmentMapper.mapToDomainEntity(doctorReview.getAppointment()),
                doctorMapper.mapToDomainEntity(doctorReview.getDoctor()));
    }

    public DoctorReviewJpaEntity mapToJpaEntity(MedicalDoctorReview doctorReview) {
        return new DoctorReviewJpaEntity(
                doctorReview.getId(),
                doctorReview.getRating(),
                appointmentMapper.mapToJpaEntity(doctorReview.getAppointment()),
                doctorMapper.mapToJpaEntity(doctorReview.getDoctor()));
    }
}
