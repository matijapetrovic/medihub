package org.medihub.application.services.prescription;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.prescription.RejectPrescriptionRequestUseCase;
import org.medihub.application.ports.outgoing.prescription.DeletePrescriptionPort;
import org.medihub.application.ports.outgoing.prescription.GetPrescriptionPort;
import org.medihub.domain.Prescription;

@RequiredArgsConstructor
public class RejectPrescriptionService implements RejectPrescriptionRequestUseCase {
    private final GetPrescriptionPort getPrescriptionPort;
    private final DeletePrescriptionPort deletePrescriptionPort;

    @Override
    public void reject(Long id) {
        Prescription p = getPrescriptionPort.getPrescription(id);
        deletePrescriptionPort.delete(p);
    }
}
