package org.medihub.persistence.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.predefined_appointment.AddPredefinedAppointmentPort;
import org.medihub.application.ports.outgoing.predefined_appointment.GetPredefinedAppointmentsPort;
import org.medihub.domain.appointment.PredefinedAppointment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PredefinedAppointmentAdapter implements AddPredefinedAppointmentPort, GetPredefinedAppointmentsPort {
    private final PredefinedAppointmentRepository predefinedAppointmentRepository;
    private final PredefinedAppointmentMapper predefinedAppointmentMapper;

    @Override
    public void addPredefinedAppointment(PredefinedAppointment predefinedAppointment) {
        predefinedAppointmentRepository.save(predefinedAppointmentMapper.mapToJpaEntity(predefinedAppointment));
    }

    @Override
    public List<PredefinedAppointment> getPredefinedAppointments(Long clinicId) {
        return predefinedAppointmentRepository.findAllByMedicalDoctor_Clinic_Id(clinicId)
                .stream()
                .map(predefinedAppointmentMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
