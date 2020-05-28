package org.medihub.persistence.clinic_review;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.clinic.ClinicReview;
import org.medihub.persistence.clinic.ClinicMapper;
import org.medihub.persistence.finished_appointment.FinishedAppointmentMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClinicReviewMapper {
    private final FinishedAppointmentMapper appointmentMapper;
    private final ClinicMapper clinicMapper;

    public ClinicReview mapToDomainEntity(ClinicReviewJpaEntity clinicReview) {
        return new ClinicReview(
                clinicReview.getId(),
                clinicReview.getRating(),
                appointmentMapper.mapToDomainEntity(clinicReview.getAppointment()),
                clinicMapper.mapToDomainEntity(clinicReview.getClinic()));
    }

    public ClinicReviewJpaEntity mapToJpaEntity(ClinicReview clinicReview) {
        return new ClinicReviewJpaEntity(
                clinicReview.getId(),
                clinicReview.getRating(),
                appointmentMapper.mapToJpaEntity(clinicReview.getAppointment()),
                clinicMapper.mapToJpaEntity(clinicReview.getClinic()));
    }
}
