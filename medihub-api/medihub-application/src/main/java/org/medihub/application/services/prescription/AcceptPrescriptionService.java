package org.medihub.application.services.prescription;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.ports.incoming.prescription.AcceptPrescriptionUseCase;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.medical_nurse.GetMedicalNurseByAccountIdPort;
import org.medihub.application.ports.outgoing.prescription.GetPrescriptionPort;
import org.medihub.application.ports.outgoing.prescription.SavePrescriptionPort;
import org.medihub.domain.Prescription;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_nurse.MedicalNurse;

import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class AcceptPrescriptionService implements AcceptPrescriptionUseCase {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final GetMedicalNurseByAccountIdPort getMedicalNurseByAccountIdPort;
    private final GetPrescriptionPort getPrescriptionPort;
    private final SavePrescriptionPort savePrescriptionPort;

    @Override
    @Transactional(readOnly = false)
    public void acceptPrescription(Long id) throws ForbiddenException {
        Account account = getAuthenticatedPort.getAuthenticated();
        MedicalNurse medicalNurse = getMedicalNurseByAccountIdPort.getMedicalNurseByAccountId(account.getId());

        Prescription prescription = getPrescriptionPort.getPrescription(id);

        if(!prescription
                .getFinishedAppointment()
                .getAppointment()
                .getClinicRoom()
                .getClinic()
                .getId()
                .equals(medicalNurse.getClinic().getId())) {
            throw new ForbiddenException();
        }

        prescription.setMedicalNurse(medicalNurse);
        savePrescriptionPort.savePrescription(prescription);
    }
}
