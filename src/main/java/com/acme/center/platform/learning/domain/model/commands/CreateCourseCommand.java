package com.acme.center.platform.learning.domain.model.commands;

/**
 * Command to create a course.
 * @param title the title
 * @param description the description
 */
public record CreateCourseCommand(String title, String description) {
    /**
     * Constructor.
     * @param title the title
     *              cannot be null or blank
     * @param description the description
     *                    cannot be null or blank
     * @throws IllegalArgumentException if the title or the description is null or blank
     */
    public CreateCourseCommand {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or blank");
        }
    }
}
