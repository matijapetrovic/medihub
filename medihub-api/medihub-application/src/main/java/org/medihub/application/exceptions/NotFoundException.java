package org.medihub.application.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
    public NotFoundException() { }
}
