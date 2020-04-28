package org.medihub.persistence.medical_doctor;

import org.medihub.domain.MedicalDoctor;

public class MedicalDoctorMapper {
    public MedicalDoctor mapToDomainEntity(MedicalDoctorJpaEntity medicalDoctorJpaEntity){
        return new MedicalDoctor(
                medicalDoctorJpaEntity.getWorkingCalendar(),
                medicalDoctorJpaEntity.getClinic(),
                medicalDoctorJpaEntity.getAppointments()
                );
    }

    public MedicalDoctorJpaEntity mapToJpaEntity(MedicalDoctor medicalDoctor){
        return new MedicalDoctorJpaEntity(
                null,
                medicalDoctor.getWorkingCalendar(),
                medicalDoctor.getClinic(),
                medicalDoctor.getAppointments()
        );
    }
}
