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
        String oldPassword;
        @Password
        String newPassword;

        public ChangePasswordCommand(
                String oldPassword,
                String newPassword) {
            this.oldPassword = oldPassword;
            this.newPassword = newPassword;
            this.validateSelf();
        }
    }
}
