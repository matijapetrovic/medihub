package org.medihub.domain.clinic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.account.Account;
import org.medihub.domain.account.PersonalInfo;
import org.medihub.domain.clinic.Clinic;

@AllArgsConstructor
@Getter
public class ClinicAdmin {
    private Long id;
    private PersonalInfo personalInfo;
    private Clinic clinic;
}
