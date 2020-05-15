package org.medihub.application.ports.incoming.medical_doctor;

import lombok.Value;

@Value
public class SearchDoctorsOutput {
    Long id;
    String firstName;
    String lastName;
    Double rating;
    String from;
    String to;
}
