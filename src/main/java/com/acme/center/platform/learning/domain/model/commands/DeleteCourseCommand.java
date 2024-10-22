package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to delete a course.
 * @param courseId the course id
 */
public record DeleteCourseCommand(Long courseId) {
    /**
     * Constructor.
     * @param courseId the course id
     *                 cannot be null or less than or equal to 0
     * @throws IllegalArgumentException if the courseId is null or less than or equal to 0
     */
    public DeleteCourseCommand {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("courseId cannot be null or less than or equal to 0");
        }
    }
}
