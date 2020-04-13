package org.medihub.domain.identity;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Account {
    private AccountType type;
    private String username;
    private String password;

    public boolean authenticate(String username, String password) {
        return this.username.equalsIgnoreCase(username) &&
                this.password.equalsIgnoreCase(password);
    }
}
