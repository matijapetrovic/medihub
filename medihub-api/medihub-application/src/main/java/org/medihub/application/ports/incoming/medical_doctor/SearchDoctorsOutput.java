package org.medihub.application.ports.incoming.medical_doctor;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class SearchDoctorsOutput {
    Long id;
    String firstName;
    String lastName;
    BigDecimal rating;
    Integer ratingCount;
    String from;
    String to;
    BigDecimal appointmentPrice;
    List<String> workingTimes;
}
