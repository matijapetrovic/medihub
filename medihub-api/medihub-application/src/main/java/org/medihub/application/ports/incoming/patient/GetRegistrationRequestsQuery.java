package org.medihub.application.ports.incoming.patient;

import java.util.List;

public interface GetRegistrationRequestsQuery {
    List<GetRegistrationRequestsOutput> getRequests();
}
