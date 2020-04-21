package org.medihub.domain;

import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Getter
public class Operation extends Appointment {
    private Set<MedicalDoctor> medicalDoctors;

    public Operation(
            Patient patient,
            Date date,
            double duration,
            ClinicRoom clinicRoom,
            AppointmentType appointmentType,
            Set<Prescription> prescriptions,
            Set<MedicalDoctor> medicalDoctors) {
        super(patient, date, duration, clinicRoom, appointmentType, prescriptions);
        this.medicalDoctors = medicalDoctors;
    }
}
