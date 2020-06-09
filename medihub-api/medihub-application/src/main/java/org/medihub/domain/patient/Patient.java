package org.medihub.domain.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.account.Account;

@AllArgsConstructor
@Getter
public class Patient {
    private Long id;
    private String insuranceNumber;
    private Account account;

    public String getFullName() {
        return String.format("%s %s", account.getFirstName(), account.getLastName());
    }
}
