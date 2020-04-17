package org.medihub.web.security.authentication.dto;

import lombok.Value;

import java.util.List;

@Value
public class LoginResponse {
    String token;
    List<String> role;
    boolean passwordChanged;
    long expiresIn;
}
