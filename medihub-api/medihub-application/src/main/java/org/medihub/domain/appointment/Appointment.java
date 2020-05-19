package org.medihub.domain.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.Prescription;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.patient.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@Getter
public class Appointment {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private Patient patient;
    private MedicalDoctor doctor;
    private ClinicRoom clinicRoom;
}
