package org.medihub.application.ports.outgoing.registration_request;

import org.medihub.domain.patient.RegistrationRequest;

public interface SaveRegistrationRequestPort {
    RegistrationRequest saveRegistrationRequest(RegistrationRequest request);
}
