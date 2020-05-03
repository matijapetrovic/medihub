package org.medihub.persistence.appointment_type;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.appointment_type.SaveAppointmentTypePort;
import org.medihub.application.ports.outgoing.appointment_type.LoadAppointmentTypePort;
import org.medihub.domain.AppointmentType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentTypeAdapter implements SaveAppointmentTypePort, LoadAppointmentTypePort {
    private final AppointmentTypeMapper appointmentTypeMapper;
    private final AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public void saveAppointmentType(AppointmentType appointmentType) {
        appointmentTypeRepository.save(appointmentTypeMapper.mapToJpaEntity(appointmentType));
    }

    @Override
    public AppointmentType loadAppointmentType(Long id) {
        return null;
    }

    @Override
    public AppointmentType loadAppointmentType(String name) {
        return null;
    }
}
