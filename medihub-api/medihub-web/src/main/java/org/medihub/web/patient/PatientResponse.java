package org.medihub.web.patient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
public class PatientResponse {
    public final String firstName;
    public final String lastName;
    public final String email;
    public final String addressLine;
}
