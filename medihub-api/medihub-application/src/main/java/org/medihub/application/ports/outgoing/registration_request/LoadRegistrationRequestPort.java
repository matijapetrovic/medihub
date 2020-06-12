package org.medihub.application.ports.outgoing.registration_request;

import org.medihub.application.exceptions.NotFoundException;
import org.medihub.domain.patient.RegistrationRequest;

public interface LoadRegistrationRequestPort {
    RegistrationRequest loadById(Long requestId) throws NotFoundException;
}
