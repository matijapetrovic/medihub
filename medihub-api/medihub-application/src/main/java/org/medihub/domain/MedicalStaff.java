package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MedicalStaff extends  User {
    private WorkingCalendar workingCalendar;
    private Clinic clinic;
}
