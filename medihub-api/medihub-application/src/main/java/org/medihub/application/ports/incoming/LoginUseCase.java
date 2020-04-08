package org.medihub.application.ports.incoming;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;

import javax.validation.constraints.NotNull;

public interface LoginUseCase {
    boolean login(LoginCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class LoginCommand extends SelfValidating<LoginCommand> {

        @NotNull
        private final String username;

        @NotNull
        private final String password;

        public LoginCommand(
                String username,
                String password) {
            this.username = username;
            this.password = password;
            this.validateSelf();
        }
    }
}
