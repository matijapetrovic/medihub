package org.medihub.common.validation.validators;

import org.medihub.common.validation.annotations.Rating;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class RatingValidator implements
        ConstraintValidator<Rating, BigDecimal> {
    @Override
    public boolean isValid(BigDecimal ratingField, ConstraintValidatorContext constraintValidatorContext) {
        return ratingField != null && ratingField.compareTo(BigDecimal.ZERO) >= 0
                && ratingField.compareTo(BigDecimal.valueOf(5)) <= 0;
    }
}
