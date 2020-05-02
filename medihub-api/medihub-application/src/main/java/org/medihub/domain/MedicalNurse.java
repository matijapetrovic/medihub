package org.medihub.domain;

import lombok.AllArgsConstructor;
import org.medihub.domain.identity.Account;

public class MedicalNurse extends MedicalStaff {
    public MedicalNurse(
            Long id,
            Account account,
            WorkingCalendar workingCalendar,
            Clinic clinic) {
        super(id, account, workingCalendar, clinic);
    }
}
