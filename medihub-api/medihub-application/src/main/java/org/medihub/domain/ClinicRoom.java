package org.medihub.domain;

import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class ClinicRoom {
    private Clinic clinic;
    private Set<Appointment> appointments;
}
