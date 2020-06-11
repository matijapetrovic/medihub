package org.medihub.persistence.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.exceptions.NotFoundException;
import org.medihub.application.ports.outgoing.appointment.*;
import org.medihub.domain.appointment.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.sql.Timestamp;
import java.util.Optional;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AbstractAppointmentAdapter implements
        SaveAppointmentPort,
        LoadAppointmentPort,
        GetScheduledAppointmentsPort,
        GetCurrentAppointmentPort {
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

    public Appointment getCurrentAppointment(Long doctorId, Long patientId) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp end = Timestamp.valueOf(now);
        Timestamp start = Timestamp.valueOf(now.minusHours(1));

        Optional<AppointmentJpaEntity> appointmentJpaEntity =
                abstractAppointmentRepository.findCurrentAppointment(patientId, doctorId, start, end);
        if (appointmentJpaEntity.isEmpty())
            return null;

        return abstractAppointmentMapper.mapToDomainEntity(appointmentJpaEntity.get());
    }
}
