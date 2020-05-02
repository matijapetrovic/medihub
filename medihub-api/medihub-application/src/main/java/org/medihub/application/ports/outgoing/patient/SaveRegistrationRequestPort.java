package org.medihub.application.ports.outgoing;

import org.medihub.domain.patient.RegistrationRequest;

public interface SaveRegistrationRequestPort {
    RegistrationRequest saveRegistrationRequest(RegistrationRequest request);
}
