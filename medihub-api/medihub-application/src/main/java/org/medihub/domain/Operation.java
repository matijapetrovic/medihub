package org.medihub.domain;

import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Getter
public class Operation extends Appointment {
    private Long id;
    private Set<MedicalDoctor> medicalDoctors;

    public Operation(
            Long id,
            Long patientId,
            Date date,
            double duration,
            ClinicRoom clinicRoom,
            AppointmentType appointmentType,
            Set<Prescription> prescriptions,
            Set<MedicalDoctor> medicalDoctors) {
        super(null, patientId, date, duration, clinicRoom, appointmentType, prescriptions);
        this.medicalDoctors = medicalDoctors;
    }
}
