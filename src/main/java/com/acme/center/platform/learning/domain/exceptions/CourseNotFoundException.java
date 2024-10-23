package com.acme.center.platform.learning.domain.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long courseId) {
        super("Course with ID %s not found".formatted(courseId));
    }
}
