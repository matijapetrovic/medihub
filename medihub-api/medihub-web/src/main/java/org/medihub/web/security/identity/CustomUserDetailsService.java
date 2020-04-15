package org.medihub.web.security.identity;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.GetAccountQuery;
import org.medihub.application.ports.incoming.SaveAccountUseCase;
import org.medihub.domain.identity.Account;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final GetAccountQuery getAccountQuery;
    private final SaveAccountUseCase saveAccountUseCase;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = getAccountQuery.getAccount(email);
        if (account == null) {
            throw new UsernameNotFoundException(String.format("No user found with email: '%s'", email));
        }
        return new CustomUserDetails(account);
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String email = currentUser.getName();

        if (!reAuthenticateUser(email, oldPassword)) {
            return false;
        }

        Account account = getAccountQuery.getAccount(email);
        if (!account.changePassword(passwordEncoder.encode(newPassword))) {
            return false;
        }
        saveAccountUseCase.saveAccount(account);
        return true;
    }

    public boolean reAuthenticateUser(String email, String oldPassword) {
        if (authenticationManager == null) {
            // throw exception
            return false;
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));
        return true;
    }
}
