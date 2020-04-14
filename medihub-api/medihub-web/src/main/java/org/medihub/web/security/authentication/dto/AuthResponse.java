package org.medihub.web.security.authentication.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
public class AuthResponse {
    String token;
    long expiresIn;
}
