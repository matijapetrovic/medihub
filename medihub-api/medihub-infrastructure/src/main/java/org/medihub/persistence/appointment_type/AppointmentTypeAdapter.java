package org.medihub.persistence.appointment_type;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.LoadAppointmentTypePort;
import org.medihub.application.ports.outgoing.SaveAppointmentTypePort;
import org.medihub.domain.AppointmentType;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class AppointmentTypeAdapter implements LoadAppointmentTypePort, SaveAppointmentTypePort {
    private final AppointmentTypeMapper appointmentTypeMapper;
    private final AppointmentTypeRepository appointmentTypeRepository;


    @Override
    public AppointmentType loadAppointmentType(String name) {
        AppointmentTypeJpaEntity appointmentTypeJpaEntity =
                appointmentTypeRepository
                        .findByName(name)
                        .orElseThrow(EntityNotFoundException::new);

        return appointmentTypeMapper.mapToDomainEntity(appointmentTypeJpaEntity);
    }

    @Override
    public void saveAppointmentType(AppointmentType appointmentType) {
        appointmentTypeRepository.save(appointmentTypeMapper.mapToJpaEntity(appointmentType));
    }
}
