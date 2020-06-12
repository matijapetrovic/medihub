package org.medihub.application.ports.outgoing.patient;

import org.medihub.domain.patient.Patient;

public interface SavePatientPort {
    Patient savePatient(Patient patient);
}
