package org.medihub.common.validation.annotations;

import org.medihub.common.validation.validators.TelephoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TelephoneNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TelephoneNumber {
    String message() default "must be a valid telephone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}