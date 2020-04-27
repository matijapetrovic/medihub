package org.medihub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.identity.Account;

import java.util.Set;

@AllArgsConstructor
@Getter
public class Patient {
    private Long id;
    private Account account;
    private MedicalRecord medicalRecord;
    private Set<Appointment> appointments;
}
