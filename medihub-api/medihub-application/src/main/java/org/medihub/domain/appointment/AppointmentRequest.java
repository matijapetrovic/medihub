package org.medihub.domain.appointment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.MedicalDoctor;
import org.medihub.domain.Patient;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class AppointmentRequest {
    private Long id;
    private MedicalDoctor doctor;
    private Patient patient;
    private AppointmentType type;
    private BigDecimal money;
}
