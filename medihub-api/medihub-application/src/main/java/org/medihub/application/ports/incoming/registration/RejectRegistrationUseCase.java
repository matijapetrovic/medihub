package org.medihub.application.ports.incoming.registration;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface RejectRegistrationUseCase {
    void rejectRegistration(RejectRegistrationCommand command) throws NotFoundException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class RejectRegistrationCommand extends SelfValidating<RejectRegistrationCommand> {
        @NotNull
        Long requestId;
        @NotBlank
        String reason;

        public RejectRegistrationCommand(
                Long requestId,
                String reason) {
            this.requestId = requestId;
            this.reason = reason;
            this.validateSelf();
        }
    }
}
