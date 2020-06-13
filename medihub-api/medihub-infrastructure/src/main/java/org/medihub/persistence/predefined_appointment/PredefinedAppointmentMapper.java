package org.medihub.persistence.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.appointment.PredefinedAppointment;
import org.medihub.persistence.appointment_type.AppointmentTypeMapper;
import org.medihub.persistence.clinic_room.ClinicRoomMapper;
import org.medihub.persistence.medical_doctor.MedicalDoctorMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

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
                    predefinedAppointmentJpaEntity.getDate().toLocalDate(),
                    LocalTime.parse(predefinedAppointmentJpaEntity.getStart().toString()),
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
                Date.valueOf(predefinedAppointment.getDate()),
                Time.valueOf(predefinedAppointment.getStart()),
                predefinedAppointment.getDuration(),
                predefinedAppointment.getPrice(),
                predefinedAppointment.getDiscount(),
                clinicRoomMapper.mapToJpaEntity(predefinedAppointment.getClinicRoom()),
                appointmentTypeMapper.mapToJpaEntity(predefinedAppointment.getAppointmentType())
        );
    }
}
