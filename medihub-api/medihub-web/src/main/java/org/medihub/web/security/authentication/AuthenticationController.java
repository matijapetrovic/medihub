package org.medihub.web.security.authentication;

import lombok.RequiredArgsConstructor;
import org.medihub.web.security.TokenUtil;
import org.medihub.web.security.authentication.dto.LoginRequest;
import org.medihub.web.security.authentication.dto.LoginResponse;
import org.medihub.web.security.authentication.dto.PasswordRequest;
import org.medihub.web.security.identity.CustomGrantedAuthority;
import org.medihub.web.security.identity.CustomUserDetails;
import org.medihub.web.security.identity.CustomUserDetailsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
        final CustomUserDetails userDetails = authenticate(request);
        final LoginResponse response = createLoginResponse(userDetails);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/password")
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
}
