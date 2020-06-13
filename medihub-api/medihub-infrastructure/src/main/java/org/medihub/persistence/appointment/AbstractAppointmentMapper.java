package org.medihub.persistence.appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.Operation;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.persistence.clinic_room.ClinicRoomMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorJpaEntity;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.medihub.persistence.patient.PatientMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class AbstractAppointmentMapper {
    private final PatientMapper patientMapper;
    private final MedicalDoctorMapper medicalDoctorMapper;
    private final ClinicRoomMapper clinicRoomMapper;

    public Appointment mapToDomainEntity(AbstractAppointmentJpaEntity appointment){
        if(appointment instanceof OperationJpaEntity) {
            OperationJpaEntity operation = (OperationJpaEntity) appointment;
            return new Operation(
                operation.getId(),
                patientMapper.mapToDomainEntity(operation.getPatient()),
                medicalDoctorMapper.mapToDomainEntity(operation.getDoctor()),
                operation.getStartTime().toLocalDateTime().toLocalDate(),
                operation.getStartTime().toLocalDateTime().toLocalTime(),
                clinicRoomMapper.mapToDomainEntity(operation.getClinicRoom()),
                mapJpaDoctors(operation.getPresentDoctors()),
                appointment.getPrice()
            );
        }
        else {
            return new Appointment(
                appointment.getId(),
                appointment.getStartTime().toLocalDateTime().toLocalDate(),
                appointment.getStartTime().toLocalDateTime().toLocalTime(),
                patientMapper.mapToDomainEntity(appointment.getPatient()),
                medicalDoctorMapper.mapToDomainEntity(appointment.getDoctor()),
                clinicRoomMapper.mapToDomainEntity(appointment.getClinicRoom()),
                appointment.getPrice()
            );
        }
    }

    public AbstractAppointmentJpaEntity mapToJpaEntity(Appointment appointment){
        if(appointment instanceof Operation) {
            Operation operation = (Operation) appointment;
            return new OperationJpaEntity(
                    operation.getId(),
                    Timestamp.valueOf(LocalDateTime.of(operation.getDate() ,operation.getTime())),
                    patientMapper.mapToJpaEntity(operation.getPatient()),
                    medicalDoctorMapper.mapToJpaEntity(operation.getDoctor()),
                    clinicRoomMapper.mapToJpaEntity(operation.getClinicRoom()),
                    mapDoctors(operation.getPresentDoctors()),
                    appointment.getPrice()
            );
        }
        else {
            return new AppointmentJpaEntity(
                    appointment.getId(),
                    Timestamp.valueOf(LocalDateTime.of(appointment.getDate(), appointment.getTime())),
                    patientMapper.mapToJpaEntity(appointment.getPatient()),
                    medicalDoctorMapper.mapToJpaEntity(appointment.getDoctor()),
                    clinicRoomMapper.mapToJpaEntity(appointment.getClinicRoom()),
                    appointment.getPrice());
        }
    }

    public List<MedicalDoctorJpaEntity> mapDoctors(Set<MedicalDoctor> doctors) {
        return doctors
                .stream()
                .map(medicalDoctorMapper::mapToJpaEntity)
                .collect(Collectors.toList());
    }

    public Set<MedicalDoctor> mapJpaDoctors(List<MedicalDoctorJpaEntity> doctors) {
        return doctors
                .stream()
                .map(medicalDoctorMapper::mapToDomainEntity)
                .collect(Collectors.toSet());
    }

    public List<Appointment> mapToDomainList(List<AbstractAppointmentJpaEntity> appointmentJpaEntitySet){
        return appointmentJpaEntitySet
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    public Set<AbstractAppointmentJpaEntity> mapToJpaSet(Set<Appointment> appointments){
        return appointments
                .stream()
                .map(this::mapToJpaEntity)
                .collect(Collectors.toSet());
    }
}
