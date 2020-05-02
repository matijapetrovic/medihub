package org.medihub.application.services;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.patient.LoadPatientUseCase;
import org.medihub.application.ports.outgoing.LoadPatientPort;
import org.medihub.domain.Patient;

import java.util.List;

@RequiredArgsConstructor
public class LaodPatientService implements LoadPatientUseCase {
    private final LoadPatientPort loadPatientPort;

    @Override
    public List<Patient> loadAllPatients() {
        return loadPatientPort.loadAllPatients();
    }
}
