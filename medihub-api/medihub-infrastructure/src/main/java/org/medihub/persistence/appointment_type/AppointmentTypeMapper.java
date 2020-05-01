package org.medihub.persistence.appointment_type;

import org.medihub.domain.AppointmentType;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTypeMapper {
    public AppointmentType mapToDomainEntity(AppointmentTypeJpaEntity appointmentTypeJpaEntity){
        return new AppointmentType(
                appointmentTypeJpaEntity.getPrice());
    }

    public AppointmentTypeJpaEntity mapToJpaEntity(AppointmentType appointmentType){
        return new AppointmentTypeJpaEntity(
                null,
                appointmentType.getPrice()
        );
    }
}
