package org.medihub.application.services.medical_doctor.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.GetAllClinicMedicalDoctorsUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.util.List;

@RequiredArgsConstructor
public class GetMedicalDoctorForClinicService implements GetAllClinicMedicalDoctorsUseCase {
    private final GetDoctorsPort getDoctorsPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;

    @Override
    public List<MedicalDoctor> loadAll() {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        return getDoctorsPort.getDoctorsForClinic(clinicAdmin.getClinic().getId());
    }
}
