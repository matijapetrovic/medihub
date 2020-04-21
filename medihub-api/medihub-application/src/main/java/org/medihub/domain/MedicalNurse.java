package org.medihub.domain;

import lombok.AllArgsConstructor;

public class MedicalNurse extends MedicalStaff {
    public MedicalNurse(WorkingCalendar workingCalendar, Clinic clinic) {
        super(workingCalendar, clinic);
    }
}
