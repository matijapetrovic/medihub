package org.medihub.domain.medical_doctor;

import lombok.Getter;
import lombok.Setter;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.patient.Patient;

import java.time.LocalTime;

@Getter
@Setter
public class MedicalDoctorAppointmentScheduleItem extends MedicalDoctorScheduleItem {

    private Patient patient;
    private ClinicRoom clinicRoom;

    public MedicalDoctorAppointmentScheduleItem(Long id,
                                                LocalTime time,
                                                MedicalDoctorScheduleItemType type,
                                                Patient patient,
                                                ClinicRoom clinicRoom) {
        super(id, time, type);
        this.patient = patient;
        this.clinicRoom = clinicRoom;
    }
}
