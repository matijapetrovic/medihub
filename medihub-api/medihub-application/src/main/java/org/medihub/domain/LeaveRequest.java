package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class LeaveRequest {
    private Long id;
    private LocalDate start;
    private LocalDate end;
    private String type;
    private MedicalDoctor medicalDoctor;
}
