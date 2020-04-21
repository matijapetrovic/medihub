package org.medihub.common.validation.validators;

import org.medihub.common.validation.annotations.TelephoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelephoneNumberValidator implements
        ConstraintValidator<TelephoneNumber, String> {

    @Override
    public boolean isValid(String telephoneNumberField, ConstraintValidatorContext constraintValidatorContext) {
        return telephoneNumberField != null && telephoneNumberField.matches("((\\+381)|0)6[0-9]{7,8}");
    }
}
