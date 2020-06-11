package org.medihub.web.security.authentication;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.authentication.LoginOutput;
import org.medihub.application.ports.incoming.authentication.LoginUseCase;
import org.medihub.application.ports.incoming.authentication.LoginUseCase.LoginCommand;
import org.medihub.application.ports.incoming.account.ChangePasswordUseCase;
import org.medihub.application.ports.incoming.account.ChangePasswordUseCase.ChangePasswordCommand;
import org.medihub.web.security.TokenUtil;
import org.medihub.web.security.authentication.dto.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Component
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthenticationController {
    private final LoginUseCase loginUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;
    private final TokenUtil tokenUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginCommand command = new LoginCommand(request.getEmail(), request.getPassword());
        LoginResponse response = mapToLoginResponse(loginUseCase.login(command));
        return ResponseEntity.ok(response);
    }

    private LoginResponse mapToLoginResponse(LoginOutput output) {
        final String token = getToken(output.getEmail(), output.getClinicId());
        return new LoginResponse(
                token,
                output.getRoles(),
                output.isPasswordChanged(),
                tokenUtil.getExpiresIn());
    }

    private String getToken(String email, Long clinicId) {
        if (clinicId != null)
            return tokenUtil.generateToken(email, clinicId);
        return tokenUtil.generateToken(email);
    }

    @PostMapping("/password")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody PasswordRequest request) throws AccountNotFoundException {
        ChangePasswordCommand command = new ChangePasswordCommand(request.getOldPassword(), request.getNewPassword());
        boolean changed = changePasswordUseCase.changePassword(command);
        if (changed) {
            return ResponseEntity
                    .accepted()
                    .body(Map.of("message", "Password successfully changed"));
        }
        else {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "New password cannot be same as old password"));
        }
    }

}
