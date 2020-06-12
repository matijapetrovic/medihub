package org.medihub.application.services.registration;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.incoming.registration.GetRegistrationRequestsOutput;
import org.medihub.application.ports.incoming.registration.GetRegistrationRequestsQuery;
import org.medihub.application.ports.outgoing.registration_request.GetRegistrationRequestsPort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetRegistrationRequestsService implements GetRegistrationRequestsQuery {
    private final GetRegistrationRequestsPort getRegistrationRequestsPort;

    @Override
    public List<GetRegistrationRequestsOutput> getRequests() {
        return getRegistrationRequestsPort
                .getRequests()
                .stream()
                .map(request -> new GetRegistrationRequestsOutput(
                        request.getId(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getTelephoneNumber(),
                        request.getInsuranceNumber()
                ))
                .collect(Collectors.toList());
    }
}
