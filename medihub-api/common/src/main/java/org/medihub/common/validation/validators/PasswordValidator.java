package org.medihub.common.validation.validators;

import org.medihub.common.validation.annotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext constraintValidatorContext) {
        return passwordField != null && passwordField.length() >= 8 &&
                passwordField.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }
}
