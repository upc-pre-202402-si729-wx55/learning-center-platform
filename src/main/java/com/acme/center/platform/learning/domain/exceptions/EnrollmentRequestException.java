package com.acme.center.platform.learning.domain.exceptions;

public class EnrollmentRequestException extends RuntimeException {
    public EnrollmentRequestException(String exceptionMessage) {
        super("Error while creating the enrollment request: %s".formatted(exceptionMessage));
    }
}
