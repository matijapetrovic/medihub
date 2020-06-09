package org.medihub.application.services.prescription;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.prescription.AcceptPrescriptionUseCase;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.medical_nurse.GetMedicalNurseByAccountIdPort;
import org.medihub.application.ports.outgoing.prescription.GetPrescriptionPort;
import org.medihub.application.ports.outgoing.prescription.SavePrescriptionPort;
import org.medihub.domain.Prescription;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_nurse.MedicalNurse;

@RequiredArgsConstructor
public class AcceptPrescriptionService implements AcceptPrescriptionUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final GetMedicalNurseByAccountIdPort getMedicalNurseByAccountIdPort;
    private final GetPrescriptionPort getPrescriptionPort;
    private final SavePrescriptionPort savePrescriptionPort;

    @Override
    public void acceptPrescription(Long id) {
        Account account = getAuthenticatedPort.getAuthenticated();
        MedicalNurse medicalNurse = getMedicalNurseByAccountIdPort.getMedicalNurseByAccountId(account.getId());

        Prescription prescription = getPrescriptionPort.getPrescription(id);

        prescription.setMedicalNurse(medicalNurse);
        savePrescriptionPort.savePrescription(prescription);
    }
}
