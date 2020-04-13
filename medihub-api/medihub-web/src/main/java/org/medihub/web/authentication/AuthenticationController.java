package org.medihub.web.authentication;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.LoginUseCase;
import org.medihub.application.ports.incoming.LoginUseCase.LoginCommand;
import org.medihub.web.authentication.AuthRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

@Component
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final LoginUseCase loginUseCase;
    private final TokenUtil tokenUtil;

    @PostMapping(path="/api/login")
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticate(request);
        final String token = tokenUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    private void authenticate(AuthRequest request) {
        LoginCommand command = null;
        try {
            command = new LoginCommand(request.getUsername(), request.getPassword());
            if (!loginUseCase.login(command)) {
                throw new BadCredentialsException("INVALID CREDENTIALS");
            }
        } catch (ConstraintViolationException e) {
            throw new BadCredentialsException("INVALID CREDENTIALS");
        }
    }
}
