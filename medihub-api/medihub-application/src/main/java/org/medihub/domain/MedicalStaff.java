package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.medihub.domain.account.Account;
import org.medihub.domain.account.PersonalInfo;
import org.medihub.domain.clinic.Clinic;

@AllArgsConstructor
@Getter
public class MedicalStaff {
    private Long id;
    private PersonalInfo personalInfo;
    private Clinic clinic;
    private WorkingTime workingTime;
}
