package org.medihub.application.ports.incoming.prescription;

import org.medihub.application.exceptions.ForbiddenException;

public interface AcceptPrescriptionUseCase {
    void acceptPrescription(Long id) throws ForbiddenException;
}
