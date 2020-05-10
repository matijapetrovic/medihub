package org.medihub.application.ports.outgoing.patient;

import org.medihub.domain.Patient;

public interface LoadPatientPort {
    Patient loadPatient(Long patientId);
    Patient loadPatientByAccountId(Long accountId);
}
