package org.medihub.domain.appointment;

import lombok.Getter;
import org.medihub.domain.Prescription;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.appointment.AppointmentType;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.medical_doctor.MedicalDoctor;

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
