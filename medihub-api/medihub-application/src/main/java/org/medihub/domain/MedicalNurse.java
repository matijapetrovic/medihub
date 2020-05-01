package org.medihub.domain;

import lombok.AllArgsConstructor;
import org.medihub.domain.identity.Account;

public class MedicalNurse extends MedicalStaff {
    public MedicalNurse(Account account, WorkingCalendar workingCalendar, Clinic clinic) {
        super(account, workingCalendar, clinic);
    }
}
