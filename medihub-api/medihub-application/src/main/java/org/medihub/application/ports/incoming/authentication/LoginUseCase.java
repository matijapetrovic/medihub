package org.medihub.application.ports.incoming.authentication;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.medihub.common.SelfValidating;
import org.medihub.domain.identity.Account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface LoginUseCase {
    LoginOutput login(LoginCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class LoginCommand extends SelfValidating<LoginCommand> {
        @Email
        String email;
        @NotBlank
        String password;

        public LoginCommand(
                String email,
                String password) {
            this.email = email;
            this.password = password;
            this.validateSelf();
        }
    }
}
