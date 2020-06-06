package org.medihub.persistence.finished_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.finished_appointment.GetFinishedAppointmentsPort;
import org.medihub.application.ports.outgoing.finished_appointment.LoadFinishedAppointmentPort;
import org.medihub.application.ports.outgoing.finished_appointment.SaveFinishedAppointmentPort;
import org.medihub.domain.appointment.FinishedAppointment;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FinishedAppointmentAdapter implements
        GetFinishedAppointmentsPort,
        LoadFinishedAppointmentPort,
        SaveFinishedAppointmentPort{
    private final FinishedAppointmentMapper mapper;
    private final FinishedAppointmentRepository repository;

    @Override
    public List<FinishedAppointment> getFinishedAppointments(Long patientId) {
        return repository
                .findAllByAppointment_Patient_Id(patientId)
                .stream()
                .map(mapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<FinishedAppointment> getAllFinishedAppointmentsForCurrentClinic(Long clinicId) {
        return repository
                .findAllByAppointment_Doctor_Clinic_Id(clinicId)
                .stream()
                .map(mapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public FinishedAppointment loadFinishedAppointment(Long appointmentId) {
        FinishedAppointmentJpaEntity finishedAppointment =
                repository.findById(appointmentId)
                .orElseThrow(EntityNotFoundException::new);

        return mapper.mapToDomainEntity(finishedAppointment);
    }

    @Override
    public FinishedAppointment saveFinishedAppointment(FinishedAppointment finishedAppointment) {
        return mapper.mapToDomainEntity(repository.save(mapper.mapToJpaEntity(finishedAppointment)));
    }
}
