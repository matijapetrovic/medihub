package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MedicalStaff {
    private WorkingCalendar workingCalendar;
    private Clinic clinic;
}
