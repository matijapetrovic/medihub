package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.authentication.LoginOutput;
import org.medihub.application.ports.incoming.authentication.LoginUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.AuthenticationPort;
import org.medihub.domain.ClinicAdmin;
import org.medihub.domain.identity.Account;
import org.medihub.domain.identity.Authority;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LoginService implements LoginUseCase {
    private final AuthenticationPort authenticationPort;
    private final LoadClinicAdminPort loadClinicAdminPort;

    @Override
    public LoginOutput login(LoginCommand command) {
        Account account = authenticationPort
                .authenticate(command.getEmail(),
                        command.getPassword());

        if (account.getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getName().equals("ROLE_CLINIC_ADMIN"))) {
            ClinicAdmin clinicAdmin = loadClinicAdminPort
                    .loadClinicAdminByAccountId(account.getId());
            return new LoginOutput(
                    account.getEmail(),
                    account.isPasswordChanged(),
                    mapRoles(account.getAuthorities()),
                    clinicAdmin.getClinic().getId());
        }
        return new LoginOutput(
                account.getEmail(),
                account.isPasswordChanged(),
                mapRoles(account.getAuthorities()),
                null);
    }

    private List<String> mapRoles(List<Authority> authorities) {
        return authorities
                .stream()
                .map(Authority::getName)
                .collect(Collectors.toList());
    }
}
