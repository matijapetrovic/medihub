package org.medihub.persistence.appointment_type;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.ForbiddenException;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.outgoing.appointment_type.*;
import org.medihub.domain.appointment.AppointmentType;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AppointmentTypeAdapter implements
        SaveAppointmentTypePort,
        LoadAppointmentTypePort,
        GetAppointmentTypesPort,
        DeleteAppointmentTypePort,
        GetAppointmentTypePort {
    private final AppointmentTypeMapper appointmentTypeMapper;
    private final AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public void saveAppointmentType(AppointmentType appointmentType) {
        appointmentTypeRepository.save(appointmentTypeMapper.mapToJpaEntity(appointmentType));
    }

    @Override
    public AppointmentType loadAppointmentType(Long appointmentTypeId) {
        AppointmentTypeJpaEntity appointmentType = appointmentTypeRepository
                .findById(appointmentTypeId)
                .orElseThrow(EntityNotFoundException::new);
        return appointmentTypeMapper.mapToDomainEntity(appointmentType);
    }

    @Override
    public AppointmentType getById(Long id) {
        return appointmentTypeMapper.mapToDomainEntity(appointmentTypeRepository.findById(id).get());
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

    @Override
    public void delete(Long id) throws ForbiddenException {
        Long count = appointmentTypeRepository.countAppointmentTypeSpecializations(id);
        if(count == 0) {
            appointmentTypeRepository.deleteClinicPrices(id);
            appointmentTypeRepository.deleteAppointmentTypeWithLock(id);

        }
            else {
            throw new ForbiddenException("Appointment type can not be deleted!");
        }
    }

    @Override
    public AppointmentType getAppointmentType(Long id) {
        return appointmentTypeMapper.mapToDomainEntity(appointmentTypeRepository.getOne(id));
    }
}
