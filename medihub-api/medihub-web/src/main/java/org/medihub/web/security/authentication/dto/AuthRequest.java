package org.medihub.web.security.authentication.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequest {
    private String email;
    private String password;
}