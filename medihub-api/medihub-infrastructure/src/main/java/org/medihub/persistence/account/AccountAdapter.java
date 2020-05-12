package org.medihub.persistence.account;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.account.LoadAccountPort;
import org.medihub.application.ports.outgoing.account.SaveAccountPort;
import org.medihub.domain.account.Account;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountAdapter implements LoadAccountPort, SaveAccountPort {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Override
    public Optional<Account> loadAccount(String email) {
        Optional<AccountJpaEntity> account =
                accountRepository.findByEmail(email);
        if (account.isEmpty())
            return Optional.empty();

        return Optional.of(accountMapper.mapToDomainEntity(account.get()));
    }

    @Override
    public Optional<Account> loadAccount(Long accountId) {
        Optional<AccountJpaEntity> account =
                accountRepository.findById(accountId);
        if (account.isEmpty())
            return Optional.empty();

        return Optional.of(accountMapper.mapToDomainEntity(account.get()));
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(accountMapper.mapToJpaEntity(account));
    }
}
