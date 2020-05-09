package org.medihub.persistence.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.appointment.Appointment;
import org.medihub.persistence.appointment_type.AppointmentTypeMapper;
import org.medihub.persistence.clinic_room.ClinicRoomMapper;
import org.medihub.persistence.prescription.PrescriptionMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class AppointmentMapper {
    private final ClinicRoomMapper clinicRoomMapper;
    private final AppointmentTypeMapper appointmentTypeMapper;
    private final PrescriptionMapper prescriptionMapper;

    public Appointment mapToDomainEntity(AppointmentJpaEntity appointmentJpaEntity){
        return new Appointment(
                appointmentJpaEntity.getId(),
                appointmentJpaEntity.getPatientId(),
                appointmentJpaEntity.getDate(),
                appointmentJpaEntity.getDuration(),
                clinicRoomMapper.mapToDomainEntity(appointmentJpaEntity.getClinicRoomJpaEntity()),
                appointmentTypeMapper.mapToDomainEntity(appointmentJpaEntity.getAppointmentTypeJpaEntity()),
                prescriptionMapper.mapToDomainSet(appointmentJpaEntity.getPrescriptions())
        );
    }

    public AppointmentJpaEntity mapToJpaEntity(Appointment appointment){
        return new AppointmentJpaEntity(
                null,
                appointment.getPatientId(),
                appointment.getDate(),
                appointment.getDuration(),
                clinicRoomMapper.mapToJpaEntity(appointment.getClinicRoom()),
                appointmentTypeMapper.mapToJpaEntity(appointment.getAppointmentType()),
                prescriptionMapper.mapToJpaSet(appointment.getPrescriptions())
        );
    }

    public Set<Appointment> mapToDomainSet(Set<AppointmentJpaEntity> appointmentJpaEntitySet){
        return appointmentJpaEntitySet
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toSet());
    }

    public Set<AppointmentJpaEntity> mapToJpaSet(Set<Appointment> appointments){
        return appointments
                .stream()
                .map(this::mapToJpaEntity)
                .collect(Collectors.toSet());
    }
}
