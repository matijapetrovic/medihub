package org.medihub.security;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.áuthentication.AuthenticationPort;
import org.medihub.application.ports.outgoing.áuthentication.GetAuthenticatedPort;
import org.medihub.domain.identity.Account;
import org.medihub.security.identity.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationAdapter implements AuthenticationPort, GetAuthenticatedPort {
    private final AuthenticationManager authenticationManager;

    @Override
    public Account authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return getPrincipal(authentication);
    }

    private Account getPrincipal(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal.getAccount();
    }

    @Override
    public boolean reauthenticate(String email, String password) {
        if (authenticationManager == null)
            return false;
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email ,password));
        return true;
    }

    @Override
    public String getAuthenticatedEmail() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        return currentUser.getName();
    }
}
