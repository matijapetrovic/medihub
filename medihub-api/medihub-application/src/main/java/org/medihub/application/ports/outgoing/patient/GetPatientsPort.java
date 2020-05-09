package org.medihub.application.ports.outgoing.patient;

import org.medihub.domain.Patient;
import java.util.List;

public interface GetPatientsPort {
    List<Patient> getAllPatients();
}
