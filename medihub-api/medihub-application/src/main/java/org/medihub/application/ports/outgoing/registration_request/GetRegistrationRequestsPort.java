package org.medihub.application.ports.outgoing.registration_request;

import org.medihub.domain.patient.RegistrationRequest;

import java.util.List;

public interface GetRegistrationRequestsPort {
    List<RegistrationRequest> getRequests();
}
