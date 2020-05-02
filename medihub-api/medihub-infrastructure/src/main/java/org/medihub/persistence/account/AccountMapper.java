package org.medihub.persistence.account;

import org.medihub.domain.Address;
import org.medihub.domain.PersonalInfo;
import org.medihub.domain.identity.Account;
import org.medihub.domain.Authority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    public Account mapToDomainEntity(AccountJpaEntity account) {
        return new Account(
                account.getId(),
                account.getEmail(),
                account.getPassword(),
                new PersonalInfo(
                        account.getFirstName(),
                        account.getLastName(),
                        new Address(
                                account.getAddress(),
                                account.getCity(),
                                account.getCountry()),
                        account.getTelephoneNumber()),
                account.isPasswordChanged(),
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
                account.getFirstName(),
                account.getLastName(),
                account.getAddress(),
                account.getCity(),
                account.getCountry(),
                account.getTelephoneNumber(),
                account.isPasswordChanged(),
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
