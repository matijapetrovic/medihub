package org.medihub.common.validation.annotations;


import org.medihub.common.validation.validators.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "must be at least 8 characters and contain at least 1 numeric character";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
