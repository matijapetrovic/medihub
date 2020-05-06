package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.medihub.domain.identity.Account;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MedicalStaff {
    private Long id;
    private Account account;
    private WorkingCalendar workingCalendar;
    private Clinic clinic;
    private WorkingTime workingTime;
}
