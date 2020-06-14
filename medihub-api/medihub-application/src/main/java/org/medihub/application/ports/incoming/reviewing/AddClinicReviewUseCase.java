package org.medihub.application.ports.incoming.reviewing;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotAvailableException;
import org.medihub.common.SelfValidating;
import org.medihub.common.validation.annotations.Rating;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface AddClinicReviewUseCase {
    void addClinicReview(AddClinicReviewCommand command) throws ForbiddenException, NotAvailableException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class AddClinicReviewCommand extends SelfValidating<AddClinicReviewCommand> {
        @NotNull
        Long id;
        @Rating
        BigDecimal rating;

        public AddClinicReviewCommand(
                Long id,
                BigDecimal rating) {
            this.id = id;
            this.rating = rating;
            this.validateSelf();
        }
    }
}
