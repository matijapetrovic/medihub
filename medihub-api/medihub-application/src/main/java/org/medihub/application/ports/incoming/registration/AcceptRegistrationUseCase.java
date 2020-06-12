package org.medihub.application.ports.incoming.registration;

import org.medihub.application.exceptions.NotFoundException;

import javax.validation.constraints.NotNull;

public interface AcceptRegistrationUseCase {
    void acceptRegistration(@NotNull Long requestId) throws NotFoundException;
}
