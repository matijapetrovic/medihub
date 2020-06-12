package org.medihub.application.services.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.patient.LoadPatientUseCase;
import org.medihub.application.ports.incoming.patient.PatientResponse;
import org.medihub.application.ports.outgoing.patient.GetPatientsPort;
import org.medihub.domain.patient.Patient;

import java.util.List;

@RequiredArgsConstructor
public class LoadPatientService implements LoadPatientUseCase {
    private final GetPatientsPort getPatientsPort;

    @Override
    public List<Patient> loadAllPatients() {
        return getPatientsPort.getAllPatients();
    }

    @Override
    public PatientResponse loadPatientById(Long id) {
        return makePatientResponse(getPatientsPort.getPatientById(id));
    }

    private PatientResponse makePatientResponse(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getPersonalInfo().getFirstName(),
                patient.getPersonalInfo().getLastName(),
                patient.getPersonalInfo().getAccount().getEmail(),
                patient.getPersonalInfo().getAddress(),
                patient.getInsuranceNumber()
        );
    }
}
