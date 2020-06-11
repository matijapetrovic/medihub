package org.medihub.application.ports.incoming.patient;

import org.medihub.domain.patient.Patient;
import java.util.List;

public interface LoadPatientUseCase {
    List<Patient> loadAllPatients();

    PatientResponse loadPatientById(Long id);
}
