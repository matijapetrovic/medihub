package org.medihub.domain.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.medihub.domain.appointment.Appointment;
import org.medihub.domain.account.Account;

import java.util.Set;

@AllArgsConstructor
@Getter
public class Patient {
    private Long id;
    private String insuranceNumber;
    private Account account;
    private Set<Appointment> appointments;
}
