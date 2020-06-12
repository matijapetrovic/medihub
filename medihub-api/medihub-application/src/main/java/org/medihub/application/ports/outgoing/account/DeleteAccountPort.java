package org.medihub.application.ports.outgoing.account;

public interface DeleteAccountPort {
    void deleteAccountById(Long accountId);
}
