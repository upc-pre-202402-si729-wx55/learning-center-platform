package com.acme.center.platform.learning.domain.model.queries;

/**
 * Query to get all enrollments by course id
 */
public record GetAllEnrollmentsByCourseIdQuery(Long courseId) {
    /**
     * Constructor
     * @param courseId Course id
     *                 Cannot be null or less than or equal to 0
     * @throws IllegalArgumentException If courseId is null or less than or equal to 0
     */
    public GetAllEnrollmentsByCourseIdQuery {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("CourseId cannot be null or less than or equal to 0");
        }
    }
}
