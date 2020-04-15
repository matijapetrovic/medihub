package org.medihub.domain.identity;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Account {
    private Long id;
    private String email;
    private String password;
    private boolean passwordChanged;
    private List<Authority> authorities;

    public boolean changePassword(String newPassword) {
        if (password.equalsIgnoreCase(newPassword))
            return false;

        password = newPassword;
        return true;
    }
}
