package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.identity.Account;

@AllArgsConstructor
@Getter
public class ClinicAdmin {
    private Long id;
    private Account account;

    private Clinic clinic;
}
