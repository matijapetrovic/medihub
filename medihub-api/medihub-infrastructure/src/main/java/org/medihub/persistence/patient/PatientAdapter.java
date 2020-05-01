package org.medihub.persistence.patient;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.LoadPatientPort;
import org.medihub.domain.Patient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PatientAdapter implements LoadPatientPort {
    private final PatientMapper patientMapper;
        private final PatientRepository patientRepository;

    @Override
    public List<Patient> loadAllPatients() {
        return null;
    }
}
