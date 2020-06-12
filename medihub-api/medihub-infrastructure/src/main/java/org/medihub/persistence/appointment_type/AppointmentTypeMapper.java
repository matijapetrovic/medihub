package org.medihub.persistence.appointment_type;

import org.medihub.domain.appointment.AppointmentType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentTypeMapper {
    public AppointmentType mapToDomainEntity(AppointmentTypeJpaEntity appointmentTypeJpaEntity){
        return new AppointmentType(
                appointmentTypeJpaEntity.getId(),
                appointmentTypeJpaEntity.getName());
    }

    public AppointmentTypeJpaEntity mapToJpaEntity(AppointmentType appointmentType){
        return new AppointmentTypeJpaEntity(
                appointmentType.getId(),
                appointmentType.getName()
        );
    }

    public List<AppointmentType> mapToDomainList(List<AppointmentTypeJpaEntity> types){
        return types.stream().map(this::mapToDomainEntity).collect(Collectors.toList());
    }
}
