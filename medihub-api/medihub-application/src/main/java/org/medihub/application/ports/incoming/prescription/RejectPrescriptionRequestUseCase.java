package org.medihub.application.ports.incoming.prescription;

public interface RejectPrescriptionRequestUseCase {
    void reject(Long id);
}
