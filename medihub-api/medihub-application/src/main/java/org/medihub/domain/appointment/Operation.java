package org.medihub.domain.appointment;

import lombok.Getter;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.patient.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
public class Operation extends Appointment {
    private Set<MedicalDoctor> presentDoctors;

    public Operation(
            Long id,
            Patient patient,
            MedicalDoctor doctor,
            LocalDate date,
            LocalTime time,
            ClinicRoom clinicRoom,
            Set<MedicalDoctor> presentDoctors) {
        super(id, date, time, patient, doctor, clinicRoom);
        this.presentDoctors = presentDoctors;
    }
}
