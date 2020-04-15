package org.medihub.web.security.authentication.dto;

import lombok.*;


@Value
public class LoginRequest {
    String email;
    String password;
}