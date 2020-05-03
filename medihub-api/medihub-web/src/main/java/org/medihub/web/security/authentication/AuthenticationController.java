package org.medihub.web.security.authentication;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.authentication.LoginUseCase;
import org.medihub.application.ports.incoming.authentication.LoginUseCase.LoginCommand;
import org.medihub.application.ports.incoming.account.ChangePasswordUseCase;
import org.medihub.application.ports.incoming.account.ChangePasswordUseCase.ChangePasswordCommand;
import org.medihub.domain.identity.Account;
import org.medihub.domain.identity.Authority;
import org.medihub.web.security.TokenUtil;
import org.medihub.web.security.authentication.dto.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthenticationController {
    private final LoginUseCase loginUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;
    private final TokenUtil tokenUtil;

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginCommand command = new LoginCommand(request.getEmail(), request.getPassword());
        LoginResponse response = mapToLoginResponse(loginUseCase.login(command));
        return ResponseEntity.ok(response);
    }

    private LoginResponse mapToLoginResponse(Account account) {
        final String token = getToken(account.getEmail());
        return new LoginResponse(
                token,
                mapRoles(account.getAuthorities()),
                account.isPasswordChanged(),
                tokenUtil.getExpiresIn());
    }

    private String getToken(String email) {
        return tokenUtil.generateToken(email);
    }

    private List<String> mapRoles(List<Authority> authorities) {
        return authorities
                .stream()
                .map(Authority::getName)
                .collect(Collectors.toList());
    }

    @PostMapping("/password")
    @PreAuthorize("hasRole('ROLE_CLINIC_CENTER_ADMIN')")
    ResponseEntity<?> changePassword(@RequestBody PasswordRequest request) throws AccountNotFoundException {
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
