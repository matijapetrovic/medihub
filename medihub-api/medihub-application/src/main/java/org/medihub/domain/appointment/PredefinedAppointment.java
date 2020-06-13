package org.medihub.domain.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.clinic_room.ClinicRoom;
import org.medihub.domain.medical_doctor.MedicalDoctor;
import org.medihub.domain.patient.Patient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class PredefinedAppointment {
    private Long id;
    private MedicalDoctor doctor;
    private LocalDate date;
    private LocalTime start;
    private double duration;
    private ClinicRoom clinicRoom;
    private AppointmentType appointmentType;
    private BigDecimal price;
    private BigDecimal discount;

    public Appointment schedule(Patient patient) {
        return new Appointment(
                null,
                date,
                start,
                patient,
                doctor,
                clinicRoom,
                price.multiply(BigDecimal.ONE.subtract(this.discount)));
    }
}
