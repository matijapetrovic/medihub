package org.medihub.application.ports.incoming.registration;

import org.medihub.application.ports.incoming.registration.GetRegistrationRequestsOutput;

import java.util.List;

public interface GetRegistrationRequestsQuery {
    List<GetRegistrationRequestsOutput> getRequests();
}
