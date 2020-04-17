package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClinicRoom {
    Set<ClinicRoom> cr;
    Set<Appointment> a;
}
