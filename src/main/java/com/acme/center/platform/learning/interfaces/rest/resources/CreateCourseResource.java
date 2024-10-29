package com.acme.center.platform.learning.interfaces.rest.resources;

/**
 * Resource class for creating a course.
 */
public record CreateCourseResource(String title, String description) {
    /**
     * Validates the resource.
     * @throws IllegalArgumentException if the title or description is null or blank.
     */
    public CreateCourseResource {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
    }
}
