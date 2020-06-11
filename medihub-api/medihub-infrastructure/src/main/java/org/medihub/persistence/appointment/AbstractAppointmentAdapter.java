package org.medihub.persistence.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.appointment.DeleteAppointmentPort;
import org.medihub.application.ports.outgoing.appointment.LoadAppointmentPort;
import org.medihub.application.ports.outgoing.appointment.GetScheduledAppointmentsPort;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.domain.appointment.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AbstractAppointmentAdapter implements
        SaveAppointmentPort,
        LoadAppointmentPort,
        GetScheduledAppointmentsPort,
        DeleteAppointmentPort {

    private final AbstractAppointmentMapper abstractAppointmentMapper;
    private final AbstractAppointmentRepository abstractAppointmentRepository;

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return abstractAppointmentMapper.mapToDomainEntity(abstractAppointmentRepository.save(abstractAppointmentMapper.mapToJpaEntity(appointment)));
    }


    @Override
    public Appointment getAppointmentById(Long id) {
        return abstractAppointmentMapper.mapToDomainEntity(abstractAppointmentRepository.findById(id).get());
    }

    @Override
    public List<Appointment> getAllByClinicId(Long clinicId) {
        return abstractAppointmentMapper.mapToDomainList(abstractAppointmentRepository.findAllByDoctorClinicId(clinicId));
    }

    @Override
    public List<Appointment> getAppointments(Long patientId) {
        return abstractAppointmentRepository
                .findAllScheduledByPatientId(patientId)
                .stream()
                .map(abstractAppointmentMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        abstractAppointmentRepository.deleteById(appointmentId);
    }
}
