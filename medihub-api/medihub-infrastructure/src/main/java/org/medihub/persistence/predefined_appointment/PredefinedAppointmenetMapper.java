package org.medihub.persistence.predefined_appointment;

import lombok.RequiredArgsConstructor;
import org.medihub.domain.appointment.PredefinedAppointment;
import org.medihub.persistence.appointment_type.AppointmentTypeMapper;
import org.medihub.persistence.clinic_room.ClinicRoomMapper;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalTime;

@RequiredArgsConstructor
@Component
public class PredefinedAppointmenetMapper {
    private final AppointmentTypeMapper appointmentTypeMapper;
    private final ClinicRoomMapper clinicRoomMapper;

    public PredefinedAppointment mapToDomainEntity(PredefinedAppointmentJpaEntity predefinedAppointmentJpaEntity) {
            return new PredefinedAppointment(
                    predefinedAppointmentJpaEntity.getId(),
                    predefinedAppointmentJpaEntity.getDoctorId(),
                    LocalTime.parse(predefinedAppointmentJpaEntity.getStart().toString()),
                    predefinedAppointmentJpaEntity.getDuration(),
                    clinicRoomMapper.mapToDomainEntity(predefinedAppointmentJpaEntity.getClinicRoomJpaEntity()),
                    appointmentTypeMapper.mapToDomainEntity(predefinedAppointmentJpaEntity.getAppointmentTypeJpaEntity())
            );
    }

    public PredefinedAppointmentJpaEntity mapToJpaEntity(PredefinedAppointment predefinedAppointment) {
        return new PredefinedAppointmentJpaEntity(
                predefinedAppointment.getId(),
                predefinedAppointment.getDoctorId(),
                Time.valueOf(predefinedAppointment.getStart()),
                predefinedAppointment.getDuration(),
                clinicRoomMapper.mapToJpaEntity(predefinedAppointment.getClinicRoom()),
                appointmentTypeMapper.mapToJpaEntity(predefinedAppointment.getAppointmentType())
        );
    }
}
