package org.medihub.application.ports.incoming.medical_doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalDoctorResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String addressLine;
    private String telephone;
    private String workingTimeStarts;
    private String workingTimeEnds;
    private String clinicName;
    private String specialization;
    private Long appointmentTypeId;
    private BigDecimal rating;
    private Integer count;
}
