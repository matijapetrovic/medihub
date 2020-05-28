package org.medihub.application.ports.incoming.reviewing;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface AddClinicReviewUseCase {
    void addClinicReview(AddClinicReviewCommand command) throws ForbiddenException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddClinicReviewCommand extends SelfValidating<AddClinicReviewCommand> {
        @NotNull
        Long appointmentId;
        @NotNull
        BigDecimal rating;

        public AddClinicReviewCommand(
                Long appointmentId,
                BigDecimal rating) {
            this.appointmentId = appointmentId;
            this.rating = rating;
            this.validateSelf();
        }
    }
}
