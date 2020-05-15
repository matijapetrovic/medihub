package org.medihub.persistence.clinic;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.account.Address;
import org.medihub.domain.clinic.Clinic;
import org.medihub.domain.Money;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.persistence.appointment_type.AppointmentTypeJpaEntity;
import org.medihub.persistence.appointment_type.AppointmentTypeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClinicMapper {
    private final AppointmentTypeMapper appointmentTypeMapper;

    public Clinic mapToDomainEntity(ClinicJpaEntity clinic) {
        return new Clinic(
                clinic.getId(),
                clinic.getName(),
                new Address(
                        clinic.getAddress(),
                        clinic.getCity(),
                        clinic.getCountry()),
                clinic.getDescription(),
                mapAppointmentPricesToDomainEntities(clinic.getAppointmentTypePrices()));
    }

    public ClinicJpaEntity mapToJpaEntity(Clinic clinic) {
        return new ClinicJpaEntity(
                clinic.getId(),
                clinic.getName(),
                clinic.getAddress().getAddressLine(),
                clinic.getAddress().getCity(),
                clinic.getAddress().getCountry(),
                clinic.getDescription(),
                mapAppointmentPricesToJpaEntities(clinic.getAppointmentPrices()));
    }

    private Map<AppointmentTypeJpaEntity, BigDecimal>
            mapAppointmentPricesToJpaEntities(Map<AppointmentType, Money> appointmentTypePrices) {
        return appointmentTypePrices
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> appointmentTypeMapper.mapToJpaEntity(entry.getKey()),
                        entry -> entry.getValue().getAmount()));
    }

    private Map<AppointmentType, Money>
            mapAppointmentPricesToDomainEntities(Map<AppointmentTypeJpaEntity, BigDecimal> appointmentTypePrices) {
        return appointmentTypePrices
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> appointmentTypeMapper.mapToDomainEntity(entry.getKey()),
                        entry -> Money.of(entry.getValue())));
    }
}
