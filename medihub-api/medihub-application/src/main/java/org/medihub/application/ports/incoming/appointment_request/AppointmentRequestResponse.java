package org.medihub.application.ports.incoming.appointment_request;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.medihub.application.ports.incoming.medical_doctor.MedicalDoctorResponse;
import org.medihub.application.ports.incoming.patient.PatientResponse;

import java.math.BigDecimal;

@AllArgsConstructor
@Value
public class AppointmentRequestResponse {
    private Long id;
    private MedicalDoctorResponse doctor;
    private PatientResponse patient;
    private BigDecimal price;
    private String date;
    private String time;
    private String type;
}
