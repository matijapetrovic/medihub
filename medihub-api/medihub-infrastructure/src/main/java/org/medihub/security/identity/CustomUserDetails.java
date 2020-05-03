package org.medihub.security.identity;

import org.medihub.domain.identity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private final Account account;
    private final List<CustomGrantedAuthority> authorities;

    public CustomUserDetails(Account account) {
        this.account = account;
        this.authorities = mapAuthorities();
    }

    private List<CustomGrantedAuthority> mapAuthorities() {
        return account
                .getAuthorities()
                .stream()
                .map(CustomGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public Account getAccount() { return account; }

    public boolean isPasswordChanged() {
        return account.isPasswordChanged();
    }

    public List<CustomGrantedAuthority> getCustomGrantedAuthorities() {
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
