package org.medihub.web.security.authentication;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.ChangePasswordUseCase;
import org.medihub.application.ports.incoming.ChangePasswordUseCase.ChangePasswordCommand;
import org.medihub.application.ports.incoming.UpdateProfileUseCase;
import org.medihub.application.ports.incoming.UpdateProfileUseCase.UpdateProfileCommand;
import org.medihub.web.security.TokenUtil;
import org.medihub.web.security.authentication.dto.*;
import org.medihub.web.security.identity.CustomGrantedAuthority;
import org.medihub.web.security.identity.CustomUserDetails;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    private final ChangePasswordUseCase changePasswordUseCase;
    private final UpdateProfileUseCase updateProfileUseCase;
    private final AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        final CustomUserDetails userDetails = authenticate(request);
        final LoginResponse response = createLoginResponse(userDetails);
        return ResponseEntity.ok(response);
    }

    private CustomUserDetails authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (CustomUserDetails) authentication.getPrincipal();
    }

    private LoginResponse createLoginResponse(CustomUserDetails userDetails) {
        final String token = getToken(userDetails);
        return new LoginResponse(
                token,
                mapRoles(userDetails.getCustomGrantedAuthorities()),
                userDetails.isPasswordChanged(),
                tokenUtil.getExpiresIn());
    }

    private String getToken(CustomUserDetails userDetails) {
        return tokenUtil.generateToken(userDetails.getUsername());
    }

    private List<String> mapRoles(List<CustomGrantedAuthority> authorities) {
        return authorities
                .stream()
                .map(CustomGrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    @PostMapping("/password")
    ResponseEntity<?> changePassword(@RequestBody PasswordRequest request) throws AccountNotFoundException {
        boolean changed = changePassword(request.getOldPassword(), request.getNewPassword());
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

    public boolean changePassword(String oldPassword, String newPassword) throws AccountNotFoundException {
        String email = getCurrentUserEmail();

        if (!reAuthenticateUser(email, oldPassword))
            return false;

        ChangePasswordCommand command = new ChangePasswordCommand(email, newPassword);
        return changePasswordUseCase.changePassword(command);
    }

    public boolean reAuthenticateUser(String email, String oldPassword) {
        if (authenticationManager == null) {
            // throw exception
            return false;
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));
        return true;
    }

    @PutMapping("/profile")
    ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request)
            throws UnauthorizedException, AccountNotFoundException {
        authorizeUpdateProfile(request.getEmail());
        UpdateProfileCommand command =
                new UpdateProfileCommand(
                        request.getEmail(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getAddress(),
                        request.getCity(),
                        request.getCountry(),
                        request.getTelephoneNum());

        updateProfileUseCase.updateProfile(command);
        return ResponseEntity.noContent().build();
    }

    private void authorizeUpdateProfile(String email) throws UnauthorizedException {
        if (!email.equalsIgnoreCase(getCurrentUserEmail()))
            throw new UnauthorizedException();
    }

    public String getCurrentUserEmail() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        return currentUser.getName();
    }

}
