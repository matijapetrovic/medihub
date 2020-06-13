package org.medihub.application.ports.outgoing.reviewing;

import org.medihub.domain.clinic.ClinicReview;

public interface LoadClinicReviewPort {
    ClinicReview loadByPatientIdAndClinicId(Long patientId, Long clinicId);
    ClinicReview loadById(Long clinicReviewId);
}
