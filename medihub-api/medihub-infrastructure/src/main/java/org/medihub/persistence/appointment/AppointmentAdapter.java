package org.medihub.persistence.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.appointment.LoadAppointmentPort;
import org.medihub.application.ports.outgoing.appointment.GetScheduledAppointmentsPort;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.domain.appointment.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AppointmentAdapter implements
        SaveAppointmentPort,
        LoadAppointmentPort,
        GetScheduledAppointmentsPort {
    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentMapper.mapToDomainEntity(appointmentRepository.save(appointmentMapper.mapToJpaEntity(appointment)));
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentMapper.mapToDomainEntity(appointmentRepository.findById(id).get());
    }

    @Override
    public List<Appointment> getAllByClinicId(Long clinicId) {
        return appointmentMapper.mapToDomainList(appointmentRepository.findAllByDoctorClinicId(clinicId));
    }

    @Override
    public List<Appointment> getAppointments(Long patientId) {
        return appointmentRepository
                .findAllScheduledByPatientId(patientId)
                .stream()
                .map(appointmentMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

}
