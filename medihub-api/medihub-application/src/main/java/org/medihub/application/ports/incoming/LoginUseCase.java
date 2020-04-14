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
        private final String email;

        @NotNull
        private final String password;

        public LoginCommand(
                String email,
                String password) {
            this.email = email;
            this.password = password;
            this.validateSelf();
        }
    }
}
