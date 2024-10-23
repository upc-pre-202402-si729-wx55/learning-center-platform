package com.acme.center.platform.learning.domain.exceptions;

public class EnrollmentNotFoundException extends RuntimeException {
    public EnrollmentNotFoundException(Long enrollmentId) {
        super("Enrollment with ID %s not found".formatted(enrollmentId));
    }
}
