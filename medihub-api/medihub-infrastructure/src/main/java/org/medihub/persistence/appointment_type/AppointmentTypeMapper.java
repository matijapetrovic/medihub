package org.medihub.persistence.appointment_type;

import org.medihub.domain.appointment.AppointmentType;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTypeMapper {
    public AppointmentType mapToDomainEntity(AppointmentTypeJpaEntity appointmentTypeJpaEntity){
        return new AppointmentType(
                appointmentTypeJpaEntity.getId(),
                appointmentTypeJpaEntity.getName());
    }

    public AppointmentTypeJpaEntity mapToJpaEntity(AppointmentType appointmentType){
        return new AppointmentTypeJpaEntity(
                null,
                appointmentType.getName()
        );
    }
}
