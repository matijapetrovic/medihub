package org.medihub.persistence.account;

import org.medihub.domain.identity.Account;
import org.medihub.domain.Authority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    Account mapToDomainEntity(AccountJpaEntity account) {
        return new Account(
                account.getId(),
                account.getEmail(),
                account.getPassword(),
                account.isPasswordChanged(),
                mapToAuthorities(account.getAuthorities()));
    }

    List<Authority> mapToAuthorities(List<AuthorityJpaEntity> authorities) {
        return authorities
                .stream()
                .map(this::mapToAuthority)
                .collect(Collectors.toList());
    }

    Authority mapToAuthority(AuthorityJpaEntity authority) {
        return new Authority(
                authority.getId(),
                authority.getName());
    }

    AccountJpaEntity mapToJpaEntity(Account account) {
        return new AccountJpaEntity(
                account.getId(),
                account.getEmail(),
                account.getPassword(),
                account.isPasswordChanged(),
                mapToJpaAuthorities(account.getAuthorities()));
    }

    List<AuthorityJpaEntity> mapToJpaAuthorities(List<Authority> authorities) {
        return authorities
                .stream()
                .map(this::mapToJpaAuthority)
                .collect(Collectors.toList());
    }

    AuthorityJpaEntity mapToJpaAuthority(Authority authority) {
        return new AuthorityJpaEntity(
                authority.getId(),
                authority.getName());
    }
}
