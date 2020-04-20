package org.medihub.common.validation.validators;

import org.medihub.common.validation.annotations.InsuranceNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InsuranceNumberValidator implements
    ConstraintValidator<InsuranceNumber, String> {

    @Override
    public boolean isValid(String insuranceNumberField, ConstraintValidatorContext constraintValidatorContext) {
        return insuranceNumberField != null && insuranceNumberField.matches("[0-9]{11}");
    }
}
