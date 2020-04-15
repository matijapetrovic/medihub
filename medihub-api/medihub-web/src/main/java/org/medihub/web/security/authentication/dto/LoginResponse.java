package org.medihub.web.security.authentication.dto;

import lombok.Value;

@Value
public class LoginResponse {
    String token;
    long expiresIn;
}
