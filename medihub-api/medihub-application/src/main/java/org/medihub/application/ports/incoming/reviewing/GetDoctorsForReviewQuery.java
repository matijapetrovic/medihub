package org.medihub.application.ports.incoming.reviewing;

import java.util.List;

public interface GetDoctorsForReviewQuery {
    List<GetDoctorsForReviewOutput> getDoctorsForReview();
}
