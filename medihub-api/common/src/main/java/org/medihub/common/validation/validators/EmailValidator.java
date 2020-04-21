package org.medihub.common.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Email;

public class EmailValidator implements
        ConstraintValidator<Email, String> {

    @Override
    public void initialize(Email constraintAnnotation) {

    }

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext constraintValidatorContext) {
        return emailField != null && emailField.matches("[^@]+@[^\\.]+\\..+")
                && emailField.length() > 0 && emailField.length() < 320;
    }
}
