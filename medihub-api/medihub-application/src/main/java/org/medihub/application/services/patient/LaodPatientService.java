package org.medihub.application.services.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.patient.LoadPatientUseCase;
import org.medihub.application.ports.outgoing.patient.GetPatientsPort;
import org.medihub.domain.Patient;

import java.util.List;

@RequiredArgsConstructor
public class LaodPatientService implements LoadPatientUseCase {
    private final GetPatientsPort getPatientsPort;

    @Override
    public List<Patient> loadAllPatients() {
        return getPatientsPort.getAllPatients();
    }
}
