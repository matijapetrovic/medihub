package org.medihub.application.ports.outgoing.patient;

import org.medihub.domain.patient.Patient;

public interface LoadPatientPort {
    Patient loadPatient(Long patientId);
    Patient loadPatientByAccountId(Long accountId);
}
