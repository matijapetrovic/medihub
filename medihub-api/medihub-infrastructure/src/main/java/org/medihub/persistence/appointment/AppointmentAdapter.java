package org.medihub.persistence.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.appointment.GetAppointmentPort;
import org.medihub.application.ports.outgoing.appointment.GetCurrentAppointmentPort;
import org.medihub.application.ports.outgoing.appointment.GetScheduledAppointmentsPort;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.domain.appointment.Appointment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.aspectj.bridge.Version.getTime;

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
        List<Appointment> appointments = appointmentMapper.mapToDomainList(
                appointmentRepository.findAllByDoctorIdAndPatientId(doctorId, patientId));
        Appointment appointment = null;
        for (Appointment a: appointments) {
            if(a.getDate().equals(now.toLocalDate())
                    && a.getTime().isBefore(now.toLocalTime().plusHours(1L))
                    && a.getTime().plusHours(1L).isAfter(now.toLocalTime().plusHours(1L))) {
                appointment = a;
            }
        }
        return appointment;
    }
}
