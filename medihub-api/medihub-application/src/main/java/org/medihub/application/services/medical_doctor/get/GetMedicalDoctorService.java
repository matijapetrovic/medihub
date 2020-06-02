package org.medihub.application.services.medical_doctor.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.GetMedicalDoctorUseCase;
import org.medihub.application.ports.incoming.medical_doctor.MedicalDoctorResponse;
import org.medihub.application.ports.outgoing.LoadClinicAdminPort;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.GetAllDoctorsPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorsPort;
import org.medihub.application.ports.outgoing.doctor.LoadDoctorPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.clinic.ClinicAdmin;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetMedicalDoctorService implements GetMedicalDoctorUseCase {
    private final GetAllDoctorsPort getAllDoctorsPort;
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final LoadClinicAdminPort loadClinicAdminPort;

    @Override
    public List<MedicalDoctorResponse> loadAll() {
        Account account = getAuthenticatedPort.getAuthenticated();
        ClinicAdmin clinicAdmin = loadClinicAdminPort.loadClinicAdminByAccountId(account.getId());
        return getAllDoctorsResponse(
                getAllDoctorsPort.getAllDoctors(clinicAdmin.getClinic().getId()));
    }

    private List<MedicalDoctorResponse> getAllDoctorsResponse(List<MedicalDoctor> medicalDoctors) {
        return medicalDoctors
                .stream()
                .map(doctor -> new MedicalDoctorResponse(
                        doctor.getId(),
                        doctor.getAccount().getEmail(),
                        doctor.getAccount().getFirstName(),
                        doctor.getAccount().getLastName(),
                        doctor.getAccount().getPersonalInfo().getAddress(),
                        doctor.getAccount().getPersonalInfo().getTelephoneNumber(),
                        doctor.getWorkingTime().getFrom().toString(),
                        doctor.getWorkingTime().getTo().toString(),
                        doctor.getClinic().getName(),
                        doctor.getSpecialization().getName(),
                        doctor.getSpecialization().getId()
                ))
                .collect(Collectors.toList());
    }
}
