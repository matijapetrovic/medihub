package org.medihub.persistence.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.appointment.GetAppointmentPort;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.domain.appointment.Appointment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentAdapter implements SaveAppointmentPort, GetAppointmentPort {
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
}
