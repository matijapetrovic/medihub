package org.medihub.domain.reviewing;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Rating {
    BigDecimal rating;
    Integer count;
}
