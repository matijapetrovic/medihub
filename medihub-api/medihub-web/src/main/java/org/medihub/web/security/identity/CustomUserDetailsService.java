package org.medihub.web.security.identity;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.GetAccountQuery;
import org.medihub.domain.identity.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final GetAccountQuery getAccountQuery;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = getAccountQuery.getAccount(email);
        if (account == null) {
            throw new UsernameNotFoundException(String.format("No user found with email: '%s'", email));
        }
        return new CustomUserDetails(account);
    }
}
