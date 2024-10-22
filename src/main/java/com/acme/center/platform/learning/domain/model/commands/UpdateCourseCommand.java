package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to update a course.
 * @param courseId the course id
 * @param title the title
 * @param description the description
 */
public record UpdateCourseCommand(Long courseId, String title, String description) {
    /**
     * Constructor.
     * @param courseId the course id
     *                 cannot be null or less than or equal to 0
     * @param title the title
     *              cannot be null or blank
     * @param description the description
     *                    cannot be null or blank
     * @throws IllegalArgumentException if the courseId is null or less than or equal to 0,
     *                                  the title is null or blank,
     *                                  or the description is null or blank
     */
    public UpdateCourseCommand {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("courseId cannot be null or less than or equal to 0");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or blank");
        }
    }
}
