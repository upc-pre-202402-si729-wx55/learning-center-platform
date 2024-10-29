package com.acme.center.platform.learning.interfaces.rest.resources;

/**
 * Resource class for requesting enrollment in a course.
 */
public record RequestEnrollmentResource(String studentRecordId, Long courseId) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException if the student record ID is null or blank, or if the course ID is less than or equal to 0.
     */
    public RequestEnrollmentResource {
        if (studentRecordId == null || studentRecordId.isBlank()) {
            throw new IllegalArgumentException("Student record ID is required");
        }
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Course ID is required");
        }
    }
}
