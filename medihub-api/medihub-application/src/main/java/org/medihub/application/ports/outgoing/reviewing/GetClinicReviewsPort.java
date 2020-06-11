package org.medihub.application.ports.outgoing.reviewing;

import org.medihub.domain.clinic.ClinicReview;

import java.util.List;

public interface GetClinicReviewsPort {
    List<ClinicReview> getClinicsReviewsForReview(Long patientId);
}
