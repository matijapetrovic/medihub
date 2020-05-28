package org.medihub.domain.clinic;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ClinicRating {
    BigDecimal rating;
    Integer count;
}
