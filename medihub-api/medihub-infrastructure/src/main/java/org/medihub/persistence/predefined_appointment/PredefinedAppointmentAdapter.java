package org.medihub.persistence.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.outgoing.predefined_appointment.*;
import org.medihub.domain.appointment.PredefinedAppointment;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PredefinedAppointmentAdapter implements
        AddPredefinedAppointmentPort,
        GetPredefinedAppointmentsPort,
        LoadPredefinedAppointmentPort,
        DeletePredefinedAppointmentPort,
        GetAllPredefinedAppointmentsPort {
    private final PredefinedAppointmentRepository predefinedAppointmentRepository;
    private final PredefinedAppointmentMapper predefinedAppointmentMapper;

    @Override
    public PredefinedAppointment addPredefinedAppointment(PredefinedAppointment predefinedAppointment) {
        return predefinedAppointmentMapper.mapToDomainEntity(
                predefinedAppointmentRepository.save(predefinedAppointmentMapper.mapToJpaEntity(predefinedAppointment)));
    }

    @Override
    public List<PredefinedAppointment> getPredefinedAppointments(Long clinicId) {
        return predefinedAppointmentRepository.findAllByMedicalDoctor_Clinic_Id(clinicId)
                .stream()
                .map(predefinedAppointmentMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePredefinedAppointment(Long appointmentId) throws NotFoundException {
        try {
            predefinedAppointmentRepository
                    .deleteById(appointmentId);
        } catch (Exception ex) {
            throw new NotFoundException(String.format("Predefined appointment %d not found.", appointmentId));
        }
    }

    @Override
    public PredefinedAppointment loadPredefinedAppointment(Long appointmentId) throws NotFoundException {
        Optional<PredefinedAppointmentJpaEntity> predefinedAppointment =
                predefinedAppointmentRepository.findById(appointmentId);
        if (predefinedAppointment.isEmpty())
            throw new NotFoundException("Predefined appointment not found.");
        return predefinedAppointmentMapper.mapToDomainEntity(predefinedAppointment.get());
    }

    @Override
    public PredefinedAppointment loadByIdWithLock(Long id) throws NotFoundException {
        Optional<PredefinedAppointmentJpaEntity> predefinedAppointment =
                predefinedAppointmentRepository.findByIdWithLock(id);
        if (predefinedAppointment.isEmpty())
            throw new NotFoundException("Predefined appointment not found.");
        return predefinedAppointmentMapper.mapToDomainEntity(predefinedAppointment.get());
    }

    @Override
    public List<PredefinedAppointment> getAll() {
        return predefinedAppointmentMapper.mapToDomainList(predefinedAppointmentRepository.findAll());
    }
}
