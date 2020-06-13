package org.medihub.application.services.medical_doctor.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.GetMedicalDoctorByClinicIdOnDateUseCase;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsForClinicOnDatePort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class GetDoctorsForClinicOnDateService  implements GetMedicalDoctorByClinicIdOnDateUseCase {
    private final GetDoctorsForClinicOnDatePort getDoctorsPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;

    @Override
    public List<MedicalDoctor> loadAll(LocalDate date) {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        return getDoctorsPort.getDoctorsForClinicOnDate(clinicAdmin.getClinic().getId(), date);
    }
}
