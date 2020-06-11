package org.medihub.persistence.registration;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.patient.GetRegistrationRequestsPort;
import org.medihub.application.ports.outgoing.patient.SaveRegistrationRequestPort;
import org.medihub.domain.patient.RegistrationRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RegistrationRequestAdapter implements SaveRegistrationRequestPort, GetRegistrationRequestsPort {
    private final RegistrationRequestRepository registrationRequestRepository;
    private final RegistrationRequestMapper mapper;

    @Override
    public RegistrationRequest saveRegistrationRequest(RegistrationRequest request) {
        RegistrationRequestJpaEntity saved =
                registrationRequestRepository
                        .save(mapper.mapToJpaEntity(request));

        return mapper.mapToDomainEntity(saved);
    }

    @Override
    public List<RegistrationRequest> getRequests() {
        return registrationRequestRepository
                .findAll()
                .stream()
                .map(mapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
