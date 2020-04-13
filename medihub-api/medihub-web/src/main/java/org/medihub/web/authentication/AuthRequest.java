package org.medihub.web.authentication;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequest {
    private String username;
    private String password;
}