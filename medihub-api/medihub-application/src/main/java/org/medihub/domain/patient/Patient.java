package org.medihub.domain.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.account.Account;
import org.medihub.domain.account.PersonalInfo;

@AllArgsConstructor
@Getter
public class Patient {
    private Long id;
    private String insuranceNumber;
    private PersonalInfo personalInfo;

    public String getFullName() {
        return String.format("%s %s", personalInfo.getFirstName(), personalInfo.getLastName());
    }
}
