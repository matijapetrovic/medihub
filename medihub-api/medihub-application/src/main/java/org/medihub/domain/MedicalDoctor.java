package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.medihub.domain.identity.Account;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MedicalDoctor extends  MedicalStaff {
    private Account account;
    private Set<Appointment> appointments;

    public MedicalDoctor(
            Account account,
            WorkingCalendar workingCalendar,
            Clinic clinic,
            Set<Appointment> appointments
    ) {
        super(workingCalendar, clinic);
        this.account = account;
        this.appointments = appointments;
    }
}
