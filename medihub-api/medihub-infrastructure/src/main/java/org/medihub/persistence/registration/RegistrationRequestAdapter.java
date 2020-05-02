package org.medihub.persistence.registration;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.patient.SaveRegistrationRequestPort;
import org.medihub.domain.patient.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationRequestAdapter implements SaveRegistrationRequestPort {
    private final RegistrationRequestRepository registrationRequestRepository;
    private final RegistrationRequestMapper mapper;

    @Override
    public RegistrationRequest saveRegistrationRequest(RegistrationRequest request) {
        RegistrationRequestJpaEntity saved =
                registrationRequestRepository
                        .save(mapper.mapToJpaEntity(request));

        return mapper.mapToDomainEntity(saved);
    }
}
