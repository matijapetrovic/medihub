package org.medihub.persistence.doctor;

import org.medihub.domain.MedicalDoctor;
import org.medihub.persistence.clinic_room.ClinicRoomJpaEntity;

public class MedicalDoctorMapper {
    public MedicalDoctor mapToDomainEntity(MedicalDoctorJpaEntity medicalDoctorJpaEntity){
        return new MedicalDoctor(
                medicalDoctorJpaEntity.getWorkingCalendar(),
                medicalDoctorJpaEntity.getClinic(),
                medicalDoctorJpaEntity.getAppointments());
    }

    public MedicalDoctorJpaEntity mapToJpaEntity(MedicalDoctor medicalDoctor){
        return new MedicalDoctorJpaEntity(
                null,
                medicalDoctor.getWorkingCalendar(),
                medicalDoctor.getClinic(),
                medicalDoctor.getAppointments());
    }
}
