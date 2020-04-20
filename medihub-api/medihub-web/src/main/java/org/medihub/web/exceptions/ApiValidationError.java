package org.medihub.web.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.internal.engine.path.PathImpl;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper =  false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    ApiValidationError(ConstraintViolation<?> constraintViolation) {
        object = constraintViolation.getRootBeanClass().getSimpleName();
        field = ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().asString();
        rejectedValue = constraintViolation.getInvalidValue();
        message = constraintViolation.getMessage();
    }
}
