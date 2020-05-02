package org.medihub.application.ports.outgoing.patient;

import org.medihub.domain.patient.RegistrationRequest;

public interface SaveRegistrationRequestPort {
    RegistrationRequest saveRegistrationRequest(RegistrationRequest request);
}
