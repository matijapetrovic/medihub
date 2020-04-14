package org.medihub.web.security.authentication;

import lombok.RequiredArgsConstructor;
import org.medihub.web.security.TokenUtil;
import org.medihub.web.security.authentication.dto.AuthRequest;
import org.medihub.web.security.authentication.dto.AuthResponse;
import org.medihub.web.security.identity.CustomUserDetails;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Component
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        final String token = authenticate(request);
        return ResponseEntity.ok(new AuthResponse(token, tokenUtil.getExpiresIn()));
    }

    private String authenticate(AuthRequest request) {
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
