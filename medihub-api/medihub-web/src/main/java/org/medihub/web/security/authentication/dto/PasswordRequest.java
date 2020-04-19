package org.medihub.web.security.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordRequest {
    private String oldPassword;
    private String newPassword;
}