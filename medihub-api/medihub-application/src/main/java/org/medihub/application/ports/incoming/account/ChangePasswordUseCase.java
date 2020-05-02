package org.medihub.application.ports.incoming.account;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.common.SelfValidating;
import org.medihub.common.validation.annotations.Password;

import javax.validation.constraints.NotNull;

public interface ChangePasswordUseCase {
    boolean changePassword(ChangePasswordCommand command) throws AccountNotFoundException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class ChangePasswordCommand extends SelfValidating<ChangePasswordCommand> {

        @NotNull
        String email;
        @Password
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
