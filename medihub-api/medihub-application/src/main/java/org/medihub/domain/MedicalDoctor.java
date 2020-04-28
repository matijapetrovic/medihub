package org.medihub.domain;

import lombok.Getter;
import org.medihub.domain.identity.Account;

import java.util.Set;

@Getter
public class MedicalDoctor extends  MedicalStaff {
    private Long id;
    private Account account;
    private Set<Appointment> appointments;

    public MedicalDoctor(
            WorkingCalendar workingCalendar,
            Clinic clinic,
            Set<Appointment> appointments
    ) {
        super(workingCalendar, clinic);
        this.appointments = appointments;
    }
}
