package org.medihub.web.security.authentication.dto;

import lombok.Value;

@Value
public class AuthResponse {
    String token;
    long expiresIn;
}
