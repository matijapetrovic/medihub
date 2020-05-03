package org.medihub.web.security.identity;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.AccountNotFoundException;
import org.medihub.application.ports.incoming.account.GetAccountQuery;
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
        Account account = getAccount(email);
        return new CustomUserDetails(account);
    }

    private Account getAccount(String email) throws UsernameNotFoundException {
        try {
            return getAccountQuery.getAccount(email);
        } catch (AccountNotFoundException ex) {
            throw new UsernameNotFoundException("No account with email: %s found".formatted(email));
        }
    }
}
