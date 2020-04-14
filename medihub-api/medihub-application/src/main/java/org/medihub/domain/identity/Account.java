package org.medihub.domain.identity;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Account {
    private String email;
    private String password;
    private List<Authority> authorities;

    public boolean authenticate(String email, String password) {
        return this.email.equalsIgnoreCase(email) &&
                this.password.equalsIgnoreCase(password);
    }
}
