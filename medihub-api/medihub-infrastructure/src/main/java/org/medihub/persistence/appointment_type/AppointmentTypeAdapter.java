package org.medihub.persistence.appointment_type;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.appointment_type.GetAppointmentTypesPort;
import org.medihub.application.ports.outgoing.appointment_type.SaveAppointmentTypePort;
import org.medihub.application.ports.outgoing.appointment_type.LoadAppointmentTypePort;
import org.medihub.domain.AppointmentType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AppointmentTypeAdapter implements
        SaveAppointmentTypePort,
        LoadAppointmentTypePort,
        GetAppointmentTypesPort {
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

    @Override
    public List<AppointmentType> getAppointmentTypes() {
        return appointmentTypeRepository
                .findAll()
                .stream()
                .map(appointmentTypeMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
