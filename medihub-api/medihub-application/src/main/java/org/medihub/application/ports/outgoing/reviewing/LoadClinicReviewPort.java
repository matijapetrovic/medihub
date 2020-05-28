package org.medihub.application.ports.outgoing.reviewing;

import org.medihub.domain.clinic.ClinicReview;

public interface LoadClinicReviewPort {
    ClinicReview loadByAppointmentId(Long appointmentId);
}
