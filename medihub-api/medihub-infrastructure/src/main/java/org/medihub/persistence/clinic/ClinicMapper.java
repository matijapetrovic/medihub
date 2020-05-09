package org.medihub.persistence.clinic;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.Address;
import org.medihub.domain.Clinic;
import org.medihub.persistence.clinic_room.ClinicRoomMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicMapper {

    public Clinic mapToDomainEntity(ClinicJpaEntity clinic) {
        return new Clinic(
                clinic.getId(),
                clinic.getName(),
                new Address(
                        clinic.getAddress(),
                        clinic.getCity(),
                        clinic.getCountry()),
                clinic.getDescription());
    }

    public ClinicJpaEntity mapToJpaEntity(Clinic clinic) {
        return new ClinicJpaEntity(
                clinic.getId(),
                clinic.getName(),
                clinic.getAddress().getAddressLine(),
                clinic.getAddress().getCity(),
                clinic.getAddress().getCountry(),
                clinic.getDescription());
    }
}
