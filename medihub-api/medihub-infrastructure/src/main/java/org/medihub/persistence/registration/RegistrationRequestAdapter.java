package org.medihub.persistence.registration;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.outgoing.registration_request.DeleteRegistrationRequestPort;
import org.medihub.application.ports.outgoing.registration_request.GetRegistrationRequestsPort;
import org.medihub.application.ports.outgoing.registration_request.LoadRegistrationRequestPort;
import org.medihub.application.ports.outgoing.registration_request.SaveRegistrationRequestPort;
import org.medihub.domain.patient.RegistrationRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RegistrationRequestAdapter implements
        SaveRegistrationRequestPort,
        GetRegistrationRequestsPort,
        LoadRegistrationRequestPort,
        DeleteRegistrationRequestPort {
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

    @Override
    public void deleteById(Long requestId) {
        registrationRequestRepository.deleteById(requestId);
    }

    @Override
    public RegistrationRequest loadById(Long requestId) throws NotFoundException {
        Optional<RegistrationRequestJpaEntity> request = registrationRequestRepository.findById(requestId);

        if (request.isEmpty())
            throw new NotFoundException("Registration request not found");

        return mapper.mapToDomainEntity(request.get());
    }
}
