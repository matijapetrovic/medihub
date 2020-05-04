package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.medihub.domain.identity.Account;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicalDoctor extends  MedicalStaff {
    private Set<Appointment> appointments;

    public MedicalDoctor(
            Long id,
            Account account,
            WorkingCalendar workingCalendar,
            Clinic clinic,
            Set<Appointment> appointments
    ) {
        super(id, account, workingCalendar, clinic);
        this.appointments = appointments;
    }
}
