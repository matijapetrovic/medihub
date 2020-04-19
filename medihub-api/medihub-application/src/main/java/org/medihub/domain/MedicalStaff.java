package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MedicalStaff {
    private WorkingCalendar workingCalendar;
    private Clinic clinic;
}
