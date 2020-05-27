package org.medihub.application.ports.outgoing.reviewing;

import org.medihub.domain.clinic.ClinicReview;

public interface SaveClinicReviewPort {
    void saveClinicReview(ClinicReview clinicReview);
}
