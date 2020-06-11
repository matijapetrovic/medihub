package org.medihub.application.exceptions;

public class ForbiddenException extends Exception {
    public ForbiddenException(String msg) {
        super(msg);
    }

    public ForbiddenException() {
        super();
    }
}
