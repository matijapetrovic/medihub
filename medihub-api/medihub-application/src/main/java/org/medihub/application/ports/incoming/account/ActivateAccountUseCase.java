package org.medihub.application.ports.incoming.account;

import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;

import javax.validation.constraints.NotNull;

public interface ActivateAccountUseCase {
    void activateAccount(@NotNull Long accountId) throws NotFoundException, ForbiddenException;
}
