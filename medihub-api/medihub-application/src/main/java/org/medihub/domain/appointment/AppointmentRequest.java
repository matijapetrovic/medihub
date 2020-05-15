package org.medihub.domain.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.Money;
import org.medihub.domain.patient.Patient;

import java.time.LocalDate;
import java.time.LocalTime;


@AllArgsConstructor
@Getter
public class AppointmentRequest {
    private Long id;
    private MedicalDoctor doctor;
    private Patient patient;
    private Money price;
    private LocalDate date;
    private LocalTime time;

    public AppointmentType getAppointmentType() {
        return doctor.getSpecialization();
    }
}
