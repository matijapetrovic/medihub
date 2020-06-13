package org.medihub.persistence.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.appointment.PredefinedAppointment;
import org.medihub.persistence.appointment_type.AppointmentTypeMapper;
import org.medihub.persistence.clinic_room.ClinicRoomMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PredefinedAppointmentMapper {
    private final AppointmentTypeMapper appointmentTypeMapper;
    private final ClinicRoomMapper clinicRoomMapper;
    private final MedicalDoctorMapper medicalDoctorMapper;

    public PredefinedAppointment mapToDomainEntity(PredefinedAppointmentJpaEntity predefinedAppointmentJpaEntity) {
            return new PredefinedAppointment(
                    predefinedAppointmentJpaEntity.getId(),
                    medicalDoctorMapper.mapToDomainEntity(predefinedAppointmentJpaEntity.getMedicalDoctor()),
                    predefinedAppointmentJpaEntity.getStartTime().toLocalDateTime().toLocalDate(),
                    predefinedAppointmentJpaEntity.getStartTime().toLocalDateTime().toLocalTime(),
                    predefinedAppointmentJpaEntity.getDuration(),
                    clinicRoomMapper.mapToDomainEntity(predefinedAppointmentJpaEntity.getClinicRoomJpaEntity()),
                    appointmentTypeMapper.mapToDomainEntity(predefinedAppointmentJpaEntity.getAppointmentTypeJpaEntity()),
                    predefinedAppointmentJpaEntity.getPrice(),
                    predefinedAppointmentJpaEntity.getDiscount()
            );
    }

    public PredefinedAppointmentJpaEntity mapToJpaEntity(PredefinedAppointment predefinedAppointment) {
        return new PredefinedAppointmentJpaEntity(
                predefinedAppointment.getId(),
                medicalDoctorMapper.mapToJpaEntity(predefinedAppointment.getDoctor()),
                Timestamp.valueOf(LocalDateTime.of(predefinedAppointment.getDate(), predefinedAppointment.getStart())),
                predefinedAppointment.getDuration(),
                predefinedAppointment.getPrice(),
                predefinedAppointment.getDiscount(),
                clinicRoomMapper.mapToJpaEntity(predefinedAppointment.getClinicRoom()),
                appointmentTypeMapper.mapToJpaEntity(predefinedAppointment.getAppointmentType())
        );
    }

    public List<PredefinedAppointment> mapToDomainList(List<PredefinedAppointmentJpaEntity> appointmentJpaEntities) {
        return appointmentJpaEntities
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }

}
