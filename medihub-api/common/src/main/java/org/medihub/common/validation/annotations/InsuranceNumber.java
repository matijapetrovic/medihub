package org.medihub.common.validation.annotations;

import org.medihub.common.validation.validators.InsuranceNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InsuranceNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InsuranceNumber {
    String message() default "must be an 11-digit string";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
