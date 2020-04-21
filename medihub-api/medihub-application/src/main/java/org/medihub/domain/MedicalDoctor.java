package org.medihub.domain;

import lombok.Getter;

import java.util.Set;

@Getter
public class MedicalDoctor extends  MedicalStaff {
    private Set<Appointment> appointments;

    public MedicalDoctor(
            WorkingCalendar workingCalendar,
            Clinic clinic,
            Set<Appointment> appointments) {
        super(workingCalendar, clinic);
        this.appointments = appointments;
    }
}
