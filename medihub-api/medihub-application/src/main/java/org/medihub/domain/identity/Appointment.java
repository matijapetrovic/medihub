package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class Appointment {
    Patient patient;
    Date date;
    Clinic clinic;
    ClinicRoom clinicRoom;
    AppointmentType appointmentType;
    MedicalDoctor medicalDoctor;
    Set<Perscription> perscriptions;
}
