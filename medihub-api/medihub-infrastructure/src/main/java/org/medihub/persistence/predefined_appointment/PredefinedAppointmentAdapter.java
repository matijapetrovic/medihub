package org.medihub.persistence.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.outgoing.predefined_appointment.AddPredefinedAppointmentPort;
import org.medihub.application.ports.outgoing.predefined_appointment.DeletePredefinedAppointmentPort;
import org.medihub.application.ports.outgoing.predefined_appointment.GetPredefinedAppointmentsPort;
import org.medihub.application.ports.outgoing.predefined_appointment.LoadPredefinedAppointmentPort;
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
        DeletePredefinedAppointmentPort {
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
    public void deletePredefinedAppointment(Long appointmentId) {
        predefinedAppointmentRepository
                .deleteById(appointmentId);
    }

    @Override
    public PredefinedAppointment loadPredefinedAppointment(Long appointmentId) throws NotFoundException {
        Optional<PredefinedAppointmentJpaEntity> predefinedAppointment =
                predefinedAppointmentRepository.findById(appointmentId);
        if (predefinedAppointment.isEmpty())
            throw new NotFoundException(String.format("Predefined appointment %d not found.", appointmentId));
        return predefinedAppointmentMapper.mapToDomainEntity(predefinedAppointment.get());
    }
}
