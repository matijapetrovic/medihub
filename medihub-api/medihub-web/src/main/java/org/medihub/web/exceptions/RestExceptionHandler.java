package org.medihub.web.exceptions;

import org.medihub.web.security.authentication.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice("org.medihub.web")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleUsernameNotFound(
            UsernameNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Invalid e-mail", ex);
        return buildResponse(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolated(
            ConstraintViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Constraints violated", ex);

        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations())
            apiError.addSubError(new ApiValidationError(constraintViolation));

        return buildResponse(apiError);
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<Object> handleUnaothrized(
            UnauthorizedException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Unauthorized", ex);
        return buildResponse(apiError);

    }

    private ResponseEntity<Object> buildResponse(ApiError apiError) {
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
