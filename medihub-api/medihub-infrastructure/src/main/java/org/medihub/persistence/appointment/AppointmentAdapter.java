package org.medihub.persistence.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.appointment.SaveAppointmentPort;
import org.medihub.domain.appointment.Appointment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentAdapter implements SaveAppointmentPort {
    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;

    @Override
    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointmentMapper.mapToJpaEntity(appointment));
    }
}
