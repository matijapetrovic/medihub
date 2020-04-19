package org.medihub.application.ports.incoming;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;

public interface ChangePasswordUseCase {
    boolean changePassword(ChangePasswordCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class ChangePasswordCommand extends SelfValidating<ChangePasswordCommand> {
        @NotNull
        String email;
        @NotNull
        String newPassword;

        public ChangePasswordCommand(
                String email,
                String newPassword) {
            this.email = email;
            this.newPassword = newPassword;
            this.validateSelf();
        }
    }
}
