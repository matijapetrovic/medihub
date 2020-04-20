package org.medihub.persistence.account;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.LoadAccountPort;
import org.medihub.application.ports.outgoing.SaveAccountPort;
import org.medihub.domain.identity.Account;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class AccountAdapter implements LoadAccountPort, SaveAccountPort {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Override
    public Account loadAccount(String email) {
        System.out.println(email);
        AccountJpaEntity account =
                accountRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);

        return accountMapper.mapToDomainEntity(account);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(accountMapper.mapToJpaEntity(account));
    }
}
