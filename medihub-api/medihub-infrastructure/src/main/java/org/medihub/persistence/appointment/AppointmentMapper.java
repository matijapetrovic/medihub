package org.medihub.persistence.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.appointment.Appointment;
import org.medihub.persistence.appointment_type.AppointmentTypeMapper;
import org.medihub.persistence.clinic_room.ClinicRoomMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.medihub.persistence.patient.PatientMapper;
import org.medihub.persistence.prescription.PrescriptionMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class AppointmentMapper {
    private final PatientMapper patientMapper;
    private final MedicalDoctorMapper medicalDoctorMapper;
    private final ClinicRoomMapper clinicRoomMapper;

    public Appointment mapToDomainEntity(AppointmentJpaEntity appointment){
        return new Appointment(
                appointment.getId(),
                appointment.getStartTime().toLocalDateTime().toLocalDate(),
                appointment.getStartTime().toLocalDateTime().toLocalTime(),
                patientMapper.mapToDomainEntity(appointment.getPatient()),
                medicalDoctorMapper.mapToDomainEntity(appointment.getDoctor()),
                clinicRoomMapper.mapToDomainEntity(appointment.getClinicRoom()),
                appointment.getPrice());
    }

    public AppointmentJpaEntity mapToJpaEntity(Appointment appointment){
        return new AppointmentJpaEntity(
                appointment.getId(),
                Timestamp.valueOf(LocalDateTime.of(appointment.getDate(), appointment.getTime())),
                patientMapper.mapToJpaEntity(appointment.getPatient()),
                medicalDoctorMapper.mapToJpaEntity(appointment.getDoctor()),
                clinicRoomMapper.mapToJpaEntity(appointment.getClinicRoom()),
                appointment.getPrice());
    }

    public Set<Appointment> mapToDomainSet(Set<AppointmentJpaEntity> appointmentJpaEntitySet){
        return appointmentJpaEntitySet
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toSet());
    }
    public List<Appointment> mapToDomainList(List<AppointmentJpaEntity> appointmentJpaEntitySet){
        return appointmentJpaEntitySet
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    public Set<AppointmentJpaEntity> mapToJpaSet(Set<Appointment> appointments){
        return appointments
                .stream()
                .map(this::mapToJpaEntity)
                .collect(Collectors.toSet());
    }
}
