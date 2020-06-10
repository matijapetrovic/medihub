package org.medihub.application.ports.incoming.reviewing;

import java.util.List;

public interface GetClinicsForReviewQuery {
    List<GetClinicsForReviewOutput> getClinicsForReview();
}
