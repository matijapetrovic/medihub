package org.medihub.persistence.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.appointment.GetAppointmentPort;
import org.medihub.application.ports.outgoing.appointment.GetCurrentAppointmentPort;
import org.medihub.application.ports.outgoing.appointment.GetScheduledAppointmentsPort;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.domain.appointment.Appointment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class AppointmentAdapter implements
        SaveAppointmentPort,
        GetAppointmentPort,
        GetScheduledAppointmentsPort,
        GetCurrentAppointmentPort {
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

    @Override
    public Appointment getCurrentAppointment(Long doctorId, Long patientId) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp end = Timestamp.valueOf(now);
        Timestamp start = Timestamp.valueOf(now.minusHours(1));

        Optional<AppointmentJpaEntity> appointmentJpaEntity =
                appointmentRepository.findCurrentAppointment(patientId, doctorId, start, end);
        if (appointmentJpaEntity.isEmpty())
            return null;

        return appointmentMapper.mapToDomainEntity(appointmentJpaEntity.get());
    }
}
