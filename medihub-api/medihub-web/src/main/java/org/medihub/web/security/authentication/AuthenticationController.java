package org.medihub.web.security.authentication;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.medihub.web.security.TokenUtil;
import org.medihub.web.security.authentication.dto.LoginRequest;
import org.medihub.web.security.authentication.dto.LoginResponse;
import org.medihub.web.security.authentication.dto.PasswordRequest;
import org.medihub.web.security.authentication.dto.PasswordResponse;
import org.medihub.web.security.identity.CustomUserDetails;
import org.medihub.web.security.identity.CustomUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        final String token = authenticate(request);
        return ResponseEntity.ok(new LoginResponse(token, tokenUtil.getExpiresIn()));
    }

    @PostMapping("/password")
    @PreAuthorize("!hasRole('ROLE_PATIENT')")
    ResponseEntity<?> changePassword(@RequestBody PasswordRequest request) {
        boolean changed = userDetailsService.changePassword(request.getOldPassword(), request.getNewPassword());
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

    private String authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return getToken(authentication);
    }

    private String getToken(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return tokenUtil.generateToken(userDetails.getUsername());
    }
}
