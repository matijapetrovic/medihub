package org.medihub.application.services.medical_doctor.get;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.medical_doctor.GetPreviousPatientsQuery;
import org.medihub.application.ports.incoming.patient.PatientResponse;
import org.medihub.application.ports.outgoing.authentication.GetAuthenticatedPort;
import org.medihub.application.ports.outgoing.doctor.GetDoctorByAccountIdPort;
import org.medihub.application.ports.outgoing.doctor.GetPreviousPatientsPort;
import org.medihub.domain.account.Account;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.patient.Patient;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetPreviousPatientsService implements GetPreviousPatientsQuery {
    private final GetAuthenticatedPort getAuthenticatedPort;
    private final GetDoctorByAccountIdPort getDoctorByAccountIdPort;
    private final GetPreviousPatientsPort getPreviousPatientsPort;

    @Override
    public List<PatientResponse> getPreviousPatients() {
        Account account = getAuthenticatedPort.getAuthenticated();
        MedicalDoctor medicalDoctor = getDoctorByAccountIdPort.getDoctor(account.getId());

        List<Patient> patients = getPreviousPatientsPort.getPreviousPatients(medicalDoctor.getId());
        return createOutput(patients);
    }

    private List<PatientResponse> createOutput(List<Patient> patients) {
        return patients
                .stream()
                .map(p -> new PatientResponse(p.getId(),
                        p.getPersonalInfo().getFirstName(),
                        p.getPersonalInfo().getLastName(),
                        p.getPersonalInfo().getAccount().getEmail(),
                        p.getPersonalInfo().getAddress(),
                        p.getInsuranceNumber()))
                .collect(Collectors.toList());
    }
}
