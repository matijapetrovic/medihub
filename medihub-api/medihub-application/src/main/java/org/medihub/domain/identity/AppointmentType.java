package org.medihub.domain.identity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppointmentType {
    Set<Clinic> clinics;
    double price;
}
