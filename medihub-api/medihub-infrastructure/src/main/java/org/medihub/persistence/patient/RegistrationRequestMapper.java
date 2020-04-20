package org.medihub.persistence.patient;

import org.medihub.domain.patient.Address;
import org.medihub.domain.patient.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestMapper {
    RegistrationRequestJpaEntity mapToJpaEntity(RegistrationRequest request) {
        return new RegistrationRequestJpaEntity(
                request.getId(),
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                request.getAddress().getAddressLine(),
                request.getAddress().getCity(),
                request.getAddress().getCountry(),
                request.getTelephoneNumber(),
                request.getInsuranceNumber()
        );
    }

    RegistrationRequest mapToDomainEntity(RegistrationRequestJpaEntity request) {
        return new RegistrationRequest(
                request.getId(),
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                new Address(
                        request.getAddress(),
                        request.getCity(),
                        request.getCountry()),
                request.getTelephoneNumber(),
                request.getInsuranceNumber());
    }
}
