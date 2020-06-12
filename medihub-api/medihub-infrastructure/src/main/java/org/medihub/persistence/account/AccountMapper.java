package org.medihub.persistence.account;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.account.Address;
import org.medihub.domain.account.PersonalInfo;
import org.medihub.domain.account.Account;
import org.medihub.domain.account.Authority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    public Account mapToDomainEntity(AccountJpaEntity account) {
        return new Account(
                account.getId(),
                account.getEmail(),
                account.getPassword(),
                account.isPasswordChanged(),
                account.isActivated(),
                mapToAuthorities(account.getAuthorities()));
    }

    public List<Authority> mapToAuthorities(List<AuthorityJpaEntity> authorities) {
        return authorities
                .stream()
                .map(this::mapToAuthority)
                .collect(Collectors.toList());
    }

    public Authority mapToAuthority(AuthorityJpaEntity authority) {
        return new Authority(
                authority.getId(),
                authority.getName());
    }

    public AccountJpaEntity mapToJpaEntity(Account account) {
        return new AccountJpaEntity(
                account.getId(),
                account.getEmail(),
                account.getPassword(),
                account.isPasswordChanged(),
                account.isActivated(),
                mapToJpaAuthorities(account.getAuthorities()));
    }

    public List<AuthorityJpaEntity> mapToJpaAuthorities(List<Authority> authorities) {
        return authorities
                .stream()
                .map(this::mapToJpaAuthority)
                .collect(Collectors.toList());
    }

    public AuthorityJpaEntity mapToJpaAuthority(Authority authority) {
        return new AuthorityJpaEntity(
                authority.getId(),
                authority.getName());
    }
}
