package org.medihub.web.patient;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PatientResponse {
    public final String firstName;
    public final String lastName;
    public final String email;
    public final String addressLine;
    public final String insuranceNumber;
}
