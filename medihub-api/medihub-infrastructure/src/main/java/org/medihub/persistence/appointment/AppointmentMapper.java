package org.medihub.persistence.appointment;

import org.medihub.domain.Appointment;
import org.medihub.persistence.appointment_type.AppointmentTypeMapper;
import org.medihub.persistence.clinic_room.ClinicRoomMapper;
import org.medihub.persistence.patient.PatientMapper;
import org.medihub.persistence.prescription.PrescriptionMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {
    private final PatientMapper patientMapper = new PatientMapper();
    private final ClinicRoomMapper clinicRoomMapper = new ClinicRoomMapper();
    private final AppointmentTypeMapper appointmentTypeMapper = new AppointmentTypeMapper();
    private final PrescriptionMapper prescriptionMapper = new PrescriptionMapper();

    public Appointment mapToDomainEntity(AppointmentJpaEntity appointmentJpaEntity){
        return new Appointment(
                patientMapper.mapToDomainEntity(appointmentJpaEntity.getPatientJpaEntity()),
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
                patientMapper.mapToJpaEntity(appointment.getPatient()),
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
