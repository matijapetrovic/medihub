package org.medihub.application.ports.incoming.leave_request;

import lombok.Value;
import org.medihub.domain.medical_doctor.MedicalDoctor;

import java.time.LocalDate;

@Value
public class NurseLeaveRequestOutput {
    Long id;
    String start;
    String end;
    String type;
    String email;
    Long nurse;
}
