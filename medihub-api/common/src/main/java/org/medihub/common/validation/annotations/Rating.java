package org.medihub.common.validation.annotations;

import org.medihub.common.validation.validators.RatingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RatingValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Rating {
    String message() default "must be between 0 and 5 inclusive";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
