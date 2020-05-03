package org.medihub.application.ports.outgoing;

import org.medihub.domain.Patient;
import java.util.List;

public interface LoadPatientPort {
    List<Patient> loadAllPatients();
}
