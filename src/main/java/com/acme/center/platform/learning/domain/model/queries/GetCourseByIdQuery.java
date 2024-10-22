package com.acme.center.platform.learning.domain.model.queries;

/**
 * Query to get a course by its id.
 */
public record GetCourseByIdQuery(Long courseId) {
    /**
     * Constructor
     * @param courseId The course id.
     *                 Must not be null or less than or equal to zero.
     * @throws IllegalArgumentException If the course id is null or less than or equal to zero.
     */
    public GetCourseByIdQuery {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Course id must not be null or less than or equal to zero.");
        }
    }
}
