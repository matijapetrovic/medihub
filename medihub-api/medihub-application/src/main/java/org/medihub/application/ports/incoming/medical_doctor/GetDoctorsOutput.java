package org.medihub.application.ports.incoming.medical_doctor;

import lombok.Value;

@Value
public class GetDoctorsOutput {
    String firstName;
    String lastName;
    Double rating;
}
