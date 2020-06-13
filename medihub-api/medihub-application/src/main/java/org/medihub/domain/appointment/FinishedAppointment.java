package org.medihub.domain.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.medihub.domain.Diagnosis;
import org.medihub.domain.Prescription;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class FinishedAppointment {
    private Long id;
    private String description;
    private Appointment appointment;
    private Diagnosis diagnosis;
}
