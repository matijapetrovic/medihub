package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.medical_nurse.MedicalNurse;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class NurseLeaveRequest {
    private Long id;
    private LocalDate start;
    private LocalDate end;
    private String type;
    private MedicalNurse medicalNurse;
}
