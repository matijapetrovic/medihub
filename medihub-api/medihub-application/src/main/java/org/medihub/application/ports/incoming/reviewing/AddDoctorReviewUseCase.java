package org.medihub.application.ports.incoming.reviewing;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface AddDoctorReviewUseCase {
    void addDoctorReview(AddDoctorReviewCommand command) throws ForbiddenException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddDoctorReviewCommand extends SelfValidating<AddDoctorReviewCommand> {
        @NotNull
        Long appointmentId;
        @NotNull
        BigDecimal rating;

        public AddDoctorReviewCommand(
                Long appointmentId,
                BigDecimal rating) {
            this.appointmentId = appointmentId;
            this.rating = rating;
            this.validateSelf();
        }
    }
}