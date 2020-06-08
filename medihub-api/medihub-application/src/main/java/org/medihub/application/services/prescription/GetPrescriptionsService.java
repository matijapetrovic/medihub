package org.medihub.application.services.prescription;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.prescription.GetPrescriptionsOutput;
import org.medihub.application.ports.incoming.prescription.GetPrescriptionsQuery;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.medical_nurse.GetMedicalNurseByAccountIdPort;
import org.medihub.application.ports.outgoing.prescription.GetPrescriptionsPort;
import org.medihub.domain.Prescription;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_nurse.MedicalNurse;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetPrescriptionsService implements GetPrescriptionsQuery {
    private final GetPrescriptionsPort getPrescriptionsPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final GetMedicalNurseByAccountIdPort getMedicalNurseByAccountIdPort;

    @Override
    public List<GetPrescriptionsOutput> getPrescriptions() {
        Account account = getAuthenticatedPort.getAuthenticated();
        MedicalNurse medicalNurse = getMedicalNurseByAccountIdPort.getMedicalNurseByAccountId(account.getId());
        List<Prescription> prescriptions = getPrescriptionsPort.getPrescriptions(medicalNurse.getClinic().getId());

        return mapToOutput(prescriptions);
    }

    private List<GetPrescriptionsOutput> mapToOutput(List<Prescription> prescriptions) {
        return prescriptions
                .stream()
                .map(entry -> new GetPrescriptionsOutput(entry.getId(), entry.getDrug().getName()))
                .collect(Collectors.toList());
    }
}
