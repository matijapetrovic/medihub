package org.medihub.domain.account;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
public class Account implements Serializable {
    private Long id;
    private String email;
    private String password;
    private boolean passwordChanged;
    private boolean activated;
    private List<Authority> authorities;

    public boolean changePassword(String newPassword) {
        if (password.equalsIgnoreCase(newPassword))
            return false;

        password = newPassword;
        passwordChanged = true;
        return true;
    }

    public void activate() {
        this.activated = true;
    }
}
