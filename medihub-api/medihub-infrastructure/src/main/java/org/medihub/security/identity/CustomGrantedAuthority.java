package org.medihub.security.identity;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.account.Authority;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
    private final Authority authority;

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
