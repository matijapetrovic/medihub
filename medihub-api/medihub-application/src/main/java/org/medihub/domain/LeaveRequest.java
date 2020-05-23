package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.util.List;

@AllArgsConstructor
@Getter
public class LeaveRequest {
    private Long id;
    private List<String> dates;
    private Integer type;
    private MedicalDoctor medicalDoctor;
}
